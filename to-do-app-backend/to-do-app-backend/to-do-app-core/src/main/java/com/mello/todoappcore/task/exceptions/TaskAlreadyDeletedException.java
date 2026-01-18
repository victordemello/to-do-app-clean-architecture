package com.mello.todoappcore.task.exceptions;

import java.util.UUID;

/**
 * Exceção lançada quando uma operação é tentada em uma Task já deletada (isEnabled=false).
 * Tasks deletadas não podem sofrer alterações ou ações.
 */
public class TaskAlreadyDeletedException extends TaskDomainException {

    public TaskAlreadyDeletedException(UUID id) {
        super(String.format("Task com ID %s já foi deletada e não pode sofrer alterações", id));
    }
}
