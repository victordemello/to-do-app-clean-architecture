package com.mello.todoappcore.task.usecases;

import java.util.UUID;

/**
 * Use Case para deletar (soft delete) uma Task.
 * Apenas tasks com status CANCELED podem ser deletadas.
 */
public interface IDeleteTaskUseCase {

    /**
     * Deleta (logicamente) uma task.
     *
     * @param taskId identificador da task a ser deletada
     * @return
     */
    void execute(UUID taskId);
}
