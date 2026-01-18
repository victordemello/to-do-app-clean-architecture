package com.mello.todoappcore.task.domain.entities;

import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.exceptions.*;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Task {
    private UUID id;
    private Title title;
    private Description description;
    private TaskStatus status;
    private Boolean isEnabled;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(UUID id, Title title, Description description) {
        validateNotNull(title, "Title cannot be null");
        validateNotNull(description, "Description cannot be null");

        this.id = id;
        this.title = title;
        this.description = description;
        this.status = TaskStatus.BACKLOG;
        this.isEnabled = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Task(UUID id, Title title, Description description, TaskStatus status,
                boolean isEnabled, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.isEnabled = isEnabled;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateStatus(TaskStatus newStatus) {
        validateNotDeleted();
        validateNotNull(newStatus, "New status cannot be null");

        if (this.status.isFinalState()) {
            throw new TaskImmutableException(this.id, this.status);
        }

        if (!this.status.canTransitionTo(newStatus)) {
            throw new InvalidStatusTransitionException(this.status, newStatus);
        }

        this.status = newStatus;
        this.updatedAt = LocalDateTime.now();
    }

    public void updateDetails(Title newTitle, Description newDescription) {
        validateNotDeleted();
        validateNotNull(newTitle, "Title cannot be null");
        validateNotNull(newDescription, "Description cannot be null");

        if (this.status.isFinalState()) {
            throw new TaskImmutableException(this.id, this.status);
        }

        this.title = newTitle;
        this.description = newDescription;
        this.updatedAt = LocalDateTime.now();
    }

    public void markAsDeleted() {
        validateNotDeleted();

        if (this.status != TaskStatus.CANCELED) {
            throw new CannotDeleteNonCanceledTaskException(this.id, this.status);
        }

        this.isEnabled = false;
        this.updatedAt = LocalDateTime.now();
    }

    private void validateNotDeleted() {
        if (!this.isEnabled) {
            throw new TaskAlreadyDeletedException(this.id);
        }
    }

    private void validateNotNull(Object obj, String message) {
        if (obj == null) {
            throw new IllegalArgumentException(message);
        }
    }

    // Getters
    public UUID getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(id, task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
