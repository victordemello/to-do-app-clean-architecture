package com.mello.todoappquarkus.task.mappers;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappquarkus.task.rest.dto.output.*;

import java.util.List;

/**
 * Mapper para converter objetos de domínio em DTOs de Response.
 * Classe utilitária com métodos estáticos (não é um bean).
 */
public final class TaskResponseMapper {

    private TaskResponseMapper() {}

    public static CreateTaskResponseDto toCreateDto(Task task) {
        return new CreateTaskResponseDto(
                task.getId(),
                task.getTitle().toString(),
                task.getDescription().toString(),
                task.getStatus().toString()
        );
    }

    public static GetTaskResponseDto toGetDto(Task task) {
        return new GetTaskResponseDto(
                task.getId(),
                task.getTitle().toString(),
                task.getDescription().toString(),
                task.getStatus().toString()
        );
    }

    public static UpdateTaskResponseDto toUpdateDto(Task task) {
        return new UpdateTaskResponseDto(
                task.getId(),
                task.getTitle().toString(),
                task.getDescription().toString(),
                task.getStatus().toString()
        );
    }

    public static DeleteTaskResponseDto toDeleteDto(Task task) {
        return new DeleteTaskResponseDto(
                task.getId(),
                task.getTitle().toString(),
                task.getDescription().toString(),
                task.getStatus().toString()
        );
    }

    public static List<GetTaskResponseDto> toListDto(List<Task> tasks) {
        return tasks.stream()
                .map(TaskResponseMapper::toGetDto)
                .toList();
    }
}
