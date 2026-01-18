import { Component, input, output, effect, signal } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { TextareaModule } from 'primeng/textarea';
import { ButtonModule } from 'primeng/button';
import { Task, CreateTaskRequest, UpdateTaskRequest } from '../../models/task.model';

@Component({
  selector: 'app-task-form-dialog',
  standalone: true,
  imports: [FormsModule, DialogModule, InputTextModule, TextareaModule, ButtonModule],
  templateUrl: './task-form-dialog.component.html',
  styleUrl: './task-form-dialog.component.scss'
})
export class TaskFormDialogComponent {
  visible = input<boolean>(false);
  task = input<Task | null>(null);
  loading = input<boolean>(false);

  onHide = output<void>();
  onCreate = output<CreateTaskRequest>();
  onUpdate = output<{ id: string; request: UpdateTaskRequest }>();

  title = signal('');
  description = signal('');

  constructor() {
    effect(() => {
      const currentTask = this.task();
      if (currentTask) {
        this.title.set(currentTask.title);
        this.description.set(currentTask.description);
      } else {
        this.title.set('');
        this.description.set('');
      }
    });
  }

  get isEditMode(): boolean {
    return this.task() !== null;
  }

  get dialogTitle(): string {
    return this.isEditMode ? 'Edit Task' : 'New Task';
  }

  get isFormValid(): boolean {
    return this.title().trim().length > 0 && this.description().trim().length > 0;
  }

  handleHide(): void {
    this.title.set('');
    this.description.set('');
    this.onHide.emit();
  }

  handleSubmit(): void {
    if (!this.isFormValid) return;

    const currentTask = this.task();

    if (currentTask) {
      this.onUpdate.emit({
        id: currentTask.id,
        request: {
          title: this.title().trim(),
          description: this.description().trim(),
          status: currentTask.status
        }
      });
    } else {
      this.onCreate.emit({
        title: this.title().trim(),
        description: this.description().trim()
      });
    }
  }
}
