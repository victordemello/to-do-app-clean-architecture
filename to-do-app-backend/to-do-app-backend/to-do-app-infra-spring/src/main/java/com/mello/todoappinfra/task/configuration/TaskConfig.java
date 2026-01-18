package com.mello.todoappinfra.task.configuration;

import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.*;
import com.mello.todoappcore.task.usecases.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração Spring para injeção de dependências dos Use Cases.
 * Wire manual dos use cases para manter independência do Spring no módulo core.
 */
@Configuration
public class TaskConfig {

    @Bean
    public ICreateTaskUseCase createTaskUseCase(ITaskRepository taskRepository) {
        return new CreateTaskUseCaseImpl(taskRepository);
    }

    @Bean
    public IGetTaskUseCase getTaskUseCase(ITaskRepository taskRepository) {
        return new GetTaskUseCaseImpl(taskRepository);
    }

    @Bean
    public IUpdateTaskUseCase updateTaskUseCase(ITaskRepository taskRepository) {
        return new UpdateTaskUseCaseImpl(taskRepository);
    }

    @Bean
    public IDeleteTaskUseCase deleteTaskUseCase(ITaskRepository taskRepository) {
        return new DeleteTaskUseCaseImpl(taskRepository);
    }

    @Bean
    public IListTasksUseCase listTasksUseCase(ITaskRepository taskRepository) {
        return new ListTasksUseCaseImpl(taskRepository);
    }
}
