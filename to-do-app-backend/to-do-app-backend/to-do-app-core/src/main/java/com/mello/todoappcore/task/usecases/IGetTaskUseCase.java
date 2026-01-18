package com.mello.todoappcore.task.usecases;

import com.mello.todoappcore.task.domain.entities.Task;

import java.util.UUID;

public interface IGetTaskUseCase {
    Task execute(UUID taskId);
}
