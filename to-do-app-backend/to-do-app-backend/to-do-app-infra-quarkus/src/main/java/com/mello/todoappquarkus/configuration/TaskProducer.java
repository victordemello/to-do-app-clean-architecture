package com.mello.todoappquarkus.configuration;

import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.*;
import com.mello.todoappcore.task.usecases.impl.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;

/**
 * Configuração CDI para injeção de dependências dos Use Cases.
 * Producer que cria instâncias dos use cases mantendo independência do Quarkus no módulo core.
 * Equivalente ao TaskConfig do Spring, mas usando @Produces ao invés de @Bean.
 */
@ApplicationScoped
public class TaskProducer {

    @Inject
    ITaskRepository taskRepository;

    @Produces
    @ApplicationScoped
    public ICreateTaskUseCase createTaskUseCase() {
        return new CreateTaskUseCaseImpl(taskRepository);
    }

    @Produces
    @ApplicationScoped
    public IGetTaskUseCase getTaskUseCase() {
        return new GetTaskUseCaseImpl(taskRepository);
    }

    @Produces
    @ApplicationScoped
    public IUpdateTaskUseCase updateTaskUseCase() {
        return new UpdateTaskUseCaseImpl(taskRepository);
    }

    @Produces
    @ApplicationScoped
    public IDeleteTaskUseCase deleteTaskUseCase() {
        return new DeleteTaskUseCaseImpl(taskRepository);
    }

    @Produces
    @ApplicationScoped
    public IListTasksUseCase listTasksUseCase() {
        return new ListTasksUseCaseImpl(taskRepository);
    }
}
