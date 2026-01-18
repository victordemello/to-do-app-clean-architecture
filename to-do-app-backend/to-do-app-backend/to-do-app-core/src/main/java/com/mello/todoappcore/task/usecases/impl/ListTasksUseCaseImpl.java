package com.mello.todoappcore.task.usecases.impl;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.IListTasksUseCase;

import java.util.List;

/**
 * Implementação do caso de uso para listar todas as Tasks ativas.
 * Retorna apenas tasks com isEnabled=true.
 */
public class ListTasksUseCaseImpl implements IListTasksUseCase {
    private final ITaskRepository taskRepository;

    public ListTasksUseCaseImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> execute() {
        return taskRepository.findAllActive();
    }
}
