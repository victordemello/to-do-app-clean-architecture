package com.mello.todoappinfra.task.mappers;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappinfra.task.persistence.entities.TaskDbEntity;

public final class TaskMapper {

    private TaskMapper() {}

    public static TaskDbEntity toTaskDbEntity(Task task) {
        TaskDbEntity entity = new TaskDbEntity(
                task.getTitle().toString(),
                task.getDescription().toString(),
                task.getStatus(),
                task.isEnabled(),
                task.getCreatedAt(),
                task.getUpdatedAt()
        );
        if (task.getId() != null) {
            entity.setId(task.getId());
        }
        return entity;
    }

    public static Task toTask(TaskDbEntity entity) {
        return new Task(
                entity.getId(),
                new Title(entity.getTitle()),
                new Description(entity.getDescription()),
                entity.getStatus(),
                entity.isEnabled(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
