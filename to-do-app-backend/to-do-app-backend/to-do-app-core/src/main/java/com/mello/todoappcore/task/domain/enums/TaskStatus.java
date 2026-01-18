package com.mello.todoappcore.task.domain.enums;

public enum TaskStatus {
    BACKLOG,
    IN_PROGRESS,
    DONE,
    CANCELED;

    public boolean canTransitionTo(TaskStatus newStatus) {
        if (newStatus == null) {
            return false;
        }

        return switch (this) {
            case BACKLOG -> newStatus == IN_PROGRESS || newStatus == CANCELED;
            case IN_PROGRESS -> newStatus == DONE || newStatus == CANCELED;
            case DONE, CANCELED -> false; // Estados finais - não permitem transições
        };
    }

    public boolean isFinalState() {
        return this == DONE || this == CANCELED;
    }
}
