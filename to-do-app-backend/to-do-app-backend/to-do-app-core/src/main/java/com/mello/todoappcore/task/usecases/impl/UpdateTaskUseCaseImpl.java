package com.mello.todoappcore.task.usecases.impl;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.exceptions.TaskNotFoundException;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.IUpdateTaskUseCase;

import java.util.UUID;

/**
 * Implementação do caso de uso para atualizar uma Task.
 * Permite atualizar título, descrição e status seguindo as regras de negócio.
 */
public class UpdateTaskUseCaseImpl implements IUpdateTaskUseCase {
    private final ITaskRepository taskRepository;

    public UpdateTaskUseCaseImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(UUID taskId, Title title, Description description, TaskStatus status) {
        if (taskId == null) {
            throw new IllegalArgumentException("Task ID cannot be null");
        }

        // Busca task ativa (lança exceção se não encontrar)
        Task task = taskRepository.findActiveById(taskId)
                .orElseThrow(() -> new TaskNotFoundException(taskId));

        // Atualiza detalhes (título e descrição)
        // A validação de imutabilidade é feita dentro do método
        task.updateDetails(title, description);

        // Atualiza status apenas se diferente do atual
        // A validação de transição é feita dentro do método
        if (status != null && !task.getStatus().equals(status)) {
            task.updateStatus(status);
        }

        // Persiste as mudanças
        return taskRepository.saveTask(task);
    }
}
