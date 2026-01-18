package com.mello.todoappcore.task.usecases.impl;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.exceptions.TaskNotFoundException;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.IGetTaskUseCase;

import java.util.UUID;

/**
 * Implementação do caso de uso para buscar uma Task por ID.
 * Retorna apenas tasks ativas (isEnabled=true).
 */
public class GetTaskUseCaseImpl implements IGetTaskUseCase {
    private final ITaskRepository taskRepository;

    public GetTaskUseCaseImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        return taskRepository.findActiveById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }
}
