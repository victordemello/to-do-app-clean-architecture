package com.mello.todoappcore.task.exceptions;

import com.mello.todoappcore.task.domain.enums.TaskStatus;

import java.util.UUID;

/**
 * Exceção lançada quando tenta-se deletar uma Task que não está no status CANCELED.
 * Regra de negócio: apenas Tasks canceladas podem ser deletadas (soft delete).
 */
public class CannotDeleteNonCanceledTaskException extends TaskDomainException {

    public CannotDeleteNonCanceledTaskException(UUID id, TaskStatus currentStatus) {
        super(String.format(
                "Não é possível deletar a Task %s com status %s. Apenas Tasks com status CANCELED podem ser deletadas",
                id, currentStatus
        ));
    }
}
