import { Component, input, output, inject } from '@angular/core';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
import { Task, TaskStatus, TASK_STATUS_CONFIG } from '../../models/task.model';
import { DragDropService } from '../../services/drag-drop.service';

@Component({
  selector: 'app-task-card',
  standalone: true,
  imports: [MenuModule],
  templateUrl: './task-card.component.html',
  styleUrl: './task-card.component.scss'
})
export class TaskCardComponent {
  private readonly dragDropService = inject(DragDropService);

  task = input.required<Task>();

  onEdit = output<Task>();
  onStatusChange = output<{ task: Task; newStatus: TaskStatus }>();
  onDelete = output<Task>();

  getStatusConfig(status: TaskStatus) {
    return TASK_STATUS_CONFIG[status];
  }

  get isDraggable(): boolean {
    const status = this.task().status;
    return status !== 'DONE' && status !== 'CANCELED';
  }

  getMenuItems(): MenuItem[] {
    const currentTask = this.task();
    const items: MenuItem[] = [];

    if (currentTask.status === 'BACKLOG') {
      items.push({
        label: 'Start',
        icon: 'pi pi-play',
        command: () => this.changeStatus('IN_PROGRESS')
      });
      items.push({
        label: 'Cancel',
        icon: 'pi pi-times',
        command: () => this.changeStatus('CANCELED')
      });
    } else if (currentTask.status === 'IN_PROGRESS') {
      items.push({
        label: 'Complete',
        icon: 'pi pi-check',
        command: () => this.changeStatus('DONE')
      });
      items.push({
        label: 'Cancel',
        icon: 'pi pi-times',
        command: () => this.changeStatus('CANCELED')
      });
    }

    if (currentTask.status !== 'DONE' && currentTask.status !== 'CANCELED') {
      items.push({ separator: true });
      items.push({
        label: 'Edit',
        icon: 'pi pi-pencil',
        command: () => this.onEdit.emit(currentTask)
      });
    }

    if (currentTask.status === 'CANCELED') {
      items.push({
        label: 'Delete',
        icon: 'pi pi-trash',
        command: () => this.onDelete.emit(currentTask)
      });
    }

    return items;
  }

  private changeStatus(newStatus: TaskStatus): void {
    this.onStatusChange.emit({ task: this.task(), newStatus });
  }

  handleCardClick(): void {
    const currentTask = this.task();
    if (currentTask.status !== 'DONE' && currentTask.status !== 'CANCELED') {
      this.onEdit.emit(currentTask);
    }
  }

  onDragStart(event: DragEvent): void {
    if (!this.isDraggable) {
      event.preventDefault();
      return;
    }
    this.dragDropService.startDrag(this.task());
    if (event.dataTransfer) {
      event.dataTransfer.effectAllowed = 'move';
      event.dataTransfer.setData('text/plain', this.task().id);
    }
  }

  onDragEnd(): void {
    this.dragDropService.endDrag();
  }
}
