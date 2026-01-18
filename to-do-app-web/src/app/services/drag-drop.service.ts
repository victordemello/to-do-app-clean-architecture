import { Injectable, signal, computed } from '@angular/core';
import { Task, TaskStatus, canTransitionTo } from '../models/task.model';

@Injectable({
  providedIn: 'root'
})
export class DragDropService {
  private readonly draggedTask = signal<Task | null>(null);

  readonly isDragging = computed(() => this.draggedTask() !== null);
  readonly currentDraggedTask = computed(() => this.draggedTask());

  startDrag(task: Task): void {
    this.draggedTask.set(task);
  }

  endDrag(): void {
    this.draggedTask.set(null);
  }

  canDropOn(targetStatus: TaskStatus): boolean {
    const task = this.draggedTask();
    if (!task) return false;
    if (task.status === targetStatus) return false;
    return canTransitionTo(task.status, targetStatus);
  }

  isValidDropZone(targetStatus: TaskStatus): boolean {
    return this.canDropOn(targetStatus);
  }

  isInvalidDropZone(targetStatus: TaskStatus): boolean {
    const task = this.draggedTask();
    if (!task) return false;
    if (task.status === targetStatus) return false;
    return !canTransitionTo(task.status, targetStatus);
  }
}
