import { Component, OnInit, inject, signal, computed } from '@angular/core';
import { ToastModule } from 'primeng/toast';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { MessageService, ConfirmationService } from 'primeng/api';
import { HeaderComponent } from '../../components/header/header.component';
import { TaskColumnComponent } from '../../components/task-column/task-column.component';
import { TaskFormDialogComponent } from '../../components/task-form-dialog/task-form-dialog.component';
import { TaskService } from '../../services/task.service';
import { Task, TaskStatus, CreateTaskRequest, UpdateTaskRequest } from '../../models/task.model';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    ToastModule,
    ConfirmDialogModule,
    ProgressSpinnerModule,
    HeaderComponent,
    TaskColumnComponent,
    TaskFormDialogComponent
  ],
  providers: [MessageService, ConfirmationService],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit {
  private readonly taskService = inject(TaskService);
  private readonly messageService = inject(MessageService);
  private readonly confirmationService = inject(ConfirmationService);

  tasks = signal<Task[]>([]);
  loading = signal(true);
  formLoading = signal(false);
  dialogVisible = signal(false);
  selectedTask = signal<Task | null>(null);

  readonly columns: TaskStatus[] = ['BACKLOG', 'IN_PROGRESS', 'DONE', 'CANCELED'];

  backlogTasks = computed(() => this.tasks().filter(t => t.status === 'BACKLOG'));
  inProgressTasks = computed(() => this.tasks().filter(t => t.status === 'IN_PROGRESS'));
  doneTasks = computed(() => this.tasks().filter(t => t.status === 'DONE'));
  canceledTasks = computed(() => this.tasks().filter(t => t.status === 'CANCELED'));

  ngOnInit(): void {
    this.loadTasks();
  }

  getTasksByStatus(status: TaskStatus): Task[] {
    switch (status) {
      case 'BACKLOG': return this.backlogTasks();
      case 'IN_PROGRESS': return this.inProgressTasks();
      case 'DONE': return this.doneTasks();
      case 'CANCELED': return this.canceledTasks();
    }
  }

  loadTasks(): void {
    this.loading.set(true);
    this.taskService.getAllTasks().subscribe({
      next: (tasks) => {
        this.tasks.set(tasks);
        this.loading.set(false);
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: error.message || 'Failed to load tasks'
        });
        this.loading.set(false);
      }
    });
  }

  openNewTaskDialog(): void {
    this.selectedTask.set(null);
    this.dialogVisible.set(true);
  }

  openEditDialog(task: Task): void {
    this.selectedTask.set(task);
    this.dialogVisible.set(true);
  }

  closeDialog(): void {
    this.dialogVisible.set(false);
    this.selectedTask.set(null);
  }

  handleCreate(request: CreateTaskRequest): void {
    this.formLoading.set(true);
    this.taskService.createTask(request).subscribe({
      next: (task) => {
        this.tasks.update(tasks => [task, ...tasks]);
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Task created successfully'
        });
        this.formLoading.set(false);
        this.closeDialog();
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: error.message || 'Failed to create task'
        });
        this.formLoading.set(false);
      }
    });
  }

  handleUpdate(event: { id: string; request: UpdateTaskRequest }): void {
    this.formLoading.set(true);
    this.taskService.updateTask(event.id, event.request).subscribe({
      next: (updatedTask) => {
        this.tasks.update(tasks =>
          tasks.map(t => t.id === event.id ? updatedTask : t)
        );
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Task updated successfully'
        });
        this.formLoading.set(false);
        this.closeDialog();
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: error.message || 'Failed to update task'
        });
        this.formLoading.set(false);
      }
    });
  }

  handleStatusChange(event: { task: Task; newStatus: TaskStatus }): void {
    const request: UpdateTaskRequest = {
      title: event.task.title,
      description: event.task.description,
      status: event.newStatus
    };

    this.taskService.updateTask(event.task.id, request).subscribe({
      next: (updatedTask) => {
        this.tasks.update(tasks =>
          tasks.map(t => t.id === event.task.id ? updatedTask : t)
        );
        this.messageService.add({
          severity: 'success',
          summary: 'Success',
          detail: 'Status updated successfully'
        });
      },
      error: (error) => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: error.message || 'Failed to update status'
        });
      }
    });
  }

  handleDelete(task: Task): void {
    this.confirmationService.confirm({
      message: `Are you sure you want to delete "${task.title}"?`,
      header: 'Confirm Deletion',
      icon: 'pi pi-exclamation-triangle',
      acceptLabel: 'Delete',
      rejectLabel: 'Cancel',
      acceptButtonStyleClass: 'p-button-danger',
      accept: () => {
        this.taskService.deleteTask(task.id).subscribe({
          next: () => {
            this.tasks.update(tasks => tasks.filter(t => t.id !== task.id));
            this.messageService.add({
              severity: 'success',
              summary: 'Success',
              detail: 'Task deleted successfully'
            });
          },
          error: (error) => {
            this.messageService.add({
              severity: 'error',
              summary: 'Error',
              detail: error.message || 'Failed to delete task'
            });
          }
        });
      }
    });
  }
}
