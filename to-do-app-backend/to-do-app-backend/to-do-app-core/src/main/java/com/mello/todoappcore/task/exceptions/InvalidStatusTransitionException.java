package com.mello.todoappcore.task.exceptions;

import com.mello.todoappcore.task.domain.enums.TaskStatus;

/**
 * Exceção lançada quando uma transição de status inválida é tentada.
 * Exemplos: BACKLOG → DONE, DONE → IN_PROGRESS, CANCELED → DONE
 */
public class InvalidStatusTransitionException extends TaskDomainException {

    public InvalidStatusTransitionException(TaskStatus from, TaskStatus to) {
        super(String.format("Transição de status inválida: não é possível mudar de %s para %s", from, to));
    }
}
