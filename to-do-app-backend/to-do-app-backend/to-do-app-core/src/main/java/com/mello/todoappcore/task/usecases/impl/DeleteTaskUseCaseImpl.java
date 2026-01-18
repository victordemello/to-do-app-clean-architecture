package com.mello.todoappcore.task.usecases.impl;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.exceptions.TaskNotFoundException;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.IDeleteTaskUseCase;

import java.util.UUID;

/**
 * Implementação do caso de uso para deletar (soft delete) uma Task.
 * Valida que a task existe, está ativa e tem status CANCELED.
 */
public class DeleteTaskUseCaseImpl implements IDeleteTaskUseCase {
    private final ITaskRepository taskRepository;

    public DeleteTaskUseCaseImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void execute(UUID taskId) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        // Busca task ativa (lança exceção se não encontrar)
        Task task = taskRepository.findActiveById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        // Marca como deletada (valida status CANCELED internamente)
        task.markAsDeleted();

        // Persiste a mudança (soft delete)
        taskRepository.saveTask(task);
    }
}
