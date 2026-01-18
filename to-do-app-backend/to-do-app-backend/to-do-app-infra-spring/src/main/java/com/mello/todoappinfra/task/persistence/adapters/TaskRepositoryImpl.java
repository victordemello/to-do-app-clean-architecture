package com.mello.todoappinfra.task.persistence.adapters;

import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappinfra.task.mappers.TaskMapper;
import com.mello.todoappinfra.task.persistence.entities.TaskDbEntity;
import com.mello.todoappinfra.task.persistence.repositories.ISpringDataTaskRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementação do port ITaskRepository usando Spring Data JPA.
 * Adapter que conecta a camada de domínio com a infraestrutura de persistência.
 */
@Component
public class TaskRepositoryImpl implements ITaskRepository {

    private final ISpringDataTaskRepository taskRepository;

    public TaskRepositoryImpl(ISpringDataTaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task saveTask(Task task) {
        TaskDbEntity entity = TaskMapper.toTaskDbEntity(task);
        TaskDbEntity saved = taskRepository.save(entity);
        return TaskMapper.toTask(saved);
    }

    @Override
    public Optional<Task> findActiveById(UUID taskId) {
        return taskRepository.findByIdAndIsEnabledTrue(taskId)
                .map(TaskMapper::toTask);
    }

    @Override
    public List<Task> findAllActive() {
        return taskRepository.findAllByIsEnabledTrue()
                .stream()
                .map(TaskMapper::toTask)
                .toList();
    }
}
