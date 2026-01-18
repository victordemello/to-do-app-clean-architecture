export type TaskStatus = 'BACKLOG' | 'IN_PROGRESS' | 'DONE' | 'CANCELED';

export interface Task {
  id: string;
  title: string;
  description: string;
  status: TaskStatus;
}

export interface CreateTaskRequest {
  title: string;
  description: string;
}

export interface UpdateTaskRequest {
  title: string;
  description: string;
  status: TaskStatus;
}

export interface ApiError {
  status: number;
  message: string;
  timestamp: string;
}

export interface TaskStatusConfig {
  label: string;
  bgColor: string;
  textColor: string;
  borderColor: string;
  icon: string;
}

export const TASK_STATUS_CONFIG: Record<TaskStatus, TaskStatusConfig> = {
  BACKLOG: {
    label: 'Backlog',
    bgColor: '#f1f5f9',
    textColor: '#475569',
    borderColor: '#94a3b8',
    icon: 'pi pi-inbox'
  },
  IN_PROGRESS: {
    label: 'In Progress',
    bgColor: '#eff6ff',
    textColor: '#1d4ed8',
    borderColor: '#3b82f6',
    icon: 'pi pi-sync'
  },
  DONE: {
    label: 'Done',
    bgColor: '#f0fdf4',
    textColor: '#15803d',
    borderColor: '#22c55e',
    icon: 'pi pi-check-circle'
  },
  CANCELED: {
    label: 'Canceled',
    bgColor: '#fef2f2',
    textColor: '#b91c1c',
    borderColor: '#ef4444',
    icon: 'pi pi-times-circle'
  }
};

export const VALID_STATUS_TRANSITIONS: Record<TaskStatus, TaskStatus[]> = {
  BACKLOG: ['IN_PROGRESS', 'CANCELED'],
  IN_PROGRESS: ['DONE', 'CANCELED'],
  DONE: [],
  CANCELED: []
};

export function canTransitionTo(from: TaskStatus, to: TaskStatus): boolean {
  return VALID_STATUS_TRANSITIONS[from].includes(to);
}
