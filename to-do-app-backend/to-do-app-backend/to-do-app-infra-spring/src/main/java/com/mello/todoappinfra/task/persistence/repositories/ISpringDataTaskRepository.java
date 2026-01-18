package com.mello.todoappinfra.task.persistence.repositories;

import com.mello.todoappinfra.task.persistence.entities.TaskDbEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Repositório Spring Data JPA para TaskDbEntity.
 * Usa query methods para filtrar tasks ativas (isEnabled=true).
 */
public interface ISpringDataTaskRepository extends JpaRepository<TaskDbEntity, UUID> {

    /**
     * Busca uma task por ID apenas se estiver ativa (isEnabled=true).
     *
     * @param id o ID da task
     * @return Optional contendo a task se encontrada e ativa
     */
    Optional<TaskDbEntity> findByIdAndIsEnabledTrue(UUID id);

    /**
     * Lista todas as tasks ativas (isEnabled=true).
     *
     * @return lista de tasks ativas
     */
    List<TaskDbEntity> findAllByIsEnabledTrue();
}
