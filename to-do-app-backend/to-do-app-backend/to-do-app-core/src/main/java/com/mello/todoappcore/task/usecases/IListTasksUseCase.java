package com.mello.todoappcore.task.usecases;

import com.mello.todoappcore.task.domain.entities.Task;

import java.util.List;

public interface IListTasksUseCase {
    List<Task> execute();
}
