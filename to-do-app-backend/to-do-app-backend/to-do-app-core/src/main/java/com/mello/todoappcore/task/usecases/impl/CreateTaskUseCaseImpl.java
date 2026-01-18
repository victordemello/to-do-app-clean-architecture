package com.mello.todoappcore.task.usecases.impl;

import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.ICreateTaskUseCase;
import com.mello.todoappcore.task.domain.entities.Task;

public class CreateTaskUseCaseImpl implements ICreateTaskUseCase {

    private final ITaskRepository taskRepository;

    public CreateTaskUseCaseImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }
    @Override
    public Task execute(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null");
        }

        return taskRepository.saveTask(task);
    }
}
