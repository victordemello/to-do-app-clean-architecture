package com.mello.todoappquarkus.task.persistence.repositories;

import com.mello.todoappquarkus.task.persistence.entities.TaskEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repository para operações de persistência de Task usando JPA tradicional (EntityManager + JPQL).
 * Usa CDI (@ApplicationScoped) ao invés de Spring (@Component).
 */
@ApplicationScoped
public class TaskRepository {

    @Inject
    EntityManager em;

    @Transactional
    public TaskEntity save(TaskEntity entity) {
        if (entity.getId() == null) {
            em.persist(entity);
            return entity;
        } else {
            return em.merge(entity);
        }
    }

    public Optional<TaskEntity> findActiveById(UUID id) {
        return em.createQuery(
                        "SELECT t FROM TaskEntity t WHERE t.id = :id AND t.isEnabled = true",
                        TaskEntity.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst();
    }

    public List<TaskEntity> findAllActive() {
        return em.createQuery(
                        "SELECT t FROM TaskEntity t WHERE t.isEnabled = true ORDER BY t.createdAt DESC",
                        TaskEntity.class)
                .getResultList();
    }
}
