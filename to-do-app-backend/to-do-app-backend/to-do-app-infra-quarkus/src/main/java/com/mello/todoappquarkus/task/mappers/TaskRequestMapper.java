package com.mello.todoappquarkus.task.mappers;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappquarkus.task.rest.dto.input.CreateTaskRequestDto;

/**
 * Mapper para converter DTOs de Request em objetos de domínio.
 * Classe utilitária com métodos estáticos (não é um bean).
 */
public final class TaskRequestMapper {

    private TaskRequestMapper() {}

    public static Task toTask(CreateTaskRequestDto request) {
        return new Task(
                null,
                new Title(request.title()),
                new Description(request.description())
        );
    }

    public static Title toTitle(String title) {
        return new Title(title);
    }

    public static Description toDescription(String description) {
        return new Description(description);
    }

    public static TaskStatus toTaskStatus(String status) {
        try {
            return TaskStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                "Status inválido: " + status + ". Valores válidos: BACKLOG, IN_PROGRESS, DONE, CANCELED"
            );
        }
    }
}
