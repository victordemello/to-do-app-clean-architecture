package com.mello.todoappquarkus.task.persistence.adapters;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappquarkus.task.mappers.TaskMapper;
import com.mello.todoappquarkus.task.persistence.entities.TaskEntity;
import com.mello.todoappquarkus.task.persistence.repositories.TaskRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementação do port ITaskRepository usando Quarkus e JPA tradicional.
 * Adapter que conecta a camada de domínio com a infraestrutura de persistência.
 * Usa CDI (@ApplicationScoped) ao invés de Spring (@Component).
 */
@ApplicationScoped
public class TaskRepositoryAdapter implements ITaskRepository {

    @Inject
    TaskRepository taskRepository;

    @Transactional
    @Override
    public Task saveTask(Task task) {
        TaskEntity entity = TaskMapper.toTaskEntity(task);
        TaskEntity saved = taskRepository.save(entity);
        return TaskMapper.toTask(saved);
    }

    @Override
    public Optional<Task> findActiveById(UUID taskId) {
        return taskRepository.findActiveById(taskId)
                .map(TaskMapper::toTask);
    }

    @Override
    public List<Task> findAllActive() {
        return taskRepository.findAllActive()
                .stream()
                .map(TaskMapper::toTask)
                .toList();
    }
}
