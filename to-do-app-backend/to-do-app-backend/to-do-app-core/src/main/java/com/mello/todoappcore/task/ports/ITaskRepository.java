package com.mello.todoappcore.task.ports;

import com.mello.todoappcore.task.domain.entities.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port (interface) do repositório de Tasks seguindo Hexagonal Architecture.
 * Define o contrato para persistência de dados sem conhecer detalhes de implementação.
 *
 * A implementação (adapter) estará na camada de infraestrutura.
 *
 * Nota: saveTask() serve para create, update e soft delete (é uma mudança de estado).
 */
public interface ITaskRepository {

    /**
     * Salva uma Task (create ou update).
     * Para soft delete, usar task.markAsDeleted() e então saveTask().
     *
     * @param task a task a ser salva
     * @return a task salva com ID gerado (se novo)
     */
    Task saveTask(Task task);

    /**
     * Busca uma Task ativa por ID.
     * Retorna apenas tasks com isEnabled=true.
     *
     * @param taskId o ID da task
     * @return Optional contendo a task se encontrada e ativa, vazio caso contrário
     */
    Optional<Task> findActiveById(UUID taskId);

    /**
     * Lista todas as Tasks ativas.
     * Retorna apenas tasks com isEnabled=true.
     *
     * @return lista de tasks ativas
     */
    List<Task> findAllActive();
}
