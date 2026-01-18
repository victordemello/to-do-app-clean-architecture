package com.mello.todoappquarkus.task.mappers;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappquarkus.task.persistence.entities.TaskEntity;

/**
 * Mapper para converter entre objetos de domínio (Task) e entidades de persistência (TaskEntity).
 * Classe utilitária com métodos estáticos (não é um bean Spring/CDI).
 */
public final class TaskMapper {

    private TaskMapper() {}

    public static TaskEntity toTaskEntity(Task task) {
        TaskEntity entity = new TaskEntity(
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

    public static Task toTask(TaskEntity entity) {
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
