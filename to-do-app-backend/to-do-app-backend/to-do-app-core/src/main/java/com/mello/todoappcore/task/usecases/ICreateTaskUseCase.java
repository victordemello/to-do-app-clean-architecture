package com.mello.todoappcore.task.usecases;

import com.mello.todoappcore.task.domain.entities.Task;

public interface ICreateTaskUseCase {
    Task execute(Task task);
}
