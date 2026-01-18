import { Component, input, output, inject, computed } from '@angular/core';
import { Task, TaskStatus, TASK_STATUS_CONFIG } from '../../models/task.model';
import { TaskCardComponent } from '../task-card/task-card.component';
import { DragDropService } from '../../services/drag-drop.service';

@Component({
  selector: 'app-task-column',
  standalone: true,
  imports: [TaskCardComponent],
  templateUrl: './task-column.component.html',
  styleUrl: './task-column.component.scss'
})
export class TaskColumnComponent {
  private readonly dragDropService = inject(DragDropService);

  status = input.required<TaskStatus>();
  tasks = input.required<Task[]>();

  onEditTask = output<Task>();
  onStatusChange = output<{ task: Task; newStatus: TaskStatus }>();
  onDeleteTask = output<Task>();

  isDragOver = false;

  readonly isDragging = computed(() => this.dragDropService.isDragging());
  readonly isValidDropZone = computed(() => this.dragDropService.isValidDropZone(this.status()));
  readonly isInvalidDropZone = computed(() => this.dragDropService.isInvalidDropZone(this.status()));

  getStatusConfig() {
    return TASK_STATUS_CONFIG[this.status()];
  }

  handleEdit(task: Task): void {
    this.onEditTask.emit(task);
  }

  handleStatusChange(event: { task: Task; newStatus: TaskStatus }): void {
    this.onStatusChange.emit(event);
  }

  handleDelete(task: Task): void {
    this.onDeleteTask.emit(task);
  }

  onDragOver(event: DragEvent): void {
    if (this.isValidDropZone()) {
      event.preventDefault();
      if (event.dataTransfer) {
        event.dataTransfer.dropEffect = 'move';
      }
      this.isDragOver = true;
    }
  }

  onDragEnter(event: DragEvent): void {
    if (this.isValidDropZone()) {
      event.preventDefault();
      this.isDragOver = true;
    }
  }

  onDragLeave(event: DragEvent): void {
    const relatedTarget = event.relatedTarget as HTMLElement;
    const currentTarget = event.currentTarget as HTMLElement;

    if (!currentTarget.contains(relatedTarget)) {
      this.isDragOver = false;
    }
  }

  onDrop(event: DragEvent): void {
    event.preventDefault();
    this.isDragOver = false;

    const draggedTask = this.dragDropService.currentDraggedTask();
    if (draggedTask && this.isValidDropZone()) {
      this.onStatusChange.emit({
        task: draggedTask,
        newStatus: this.status()
      });
    }
  }
}
