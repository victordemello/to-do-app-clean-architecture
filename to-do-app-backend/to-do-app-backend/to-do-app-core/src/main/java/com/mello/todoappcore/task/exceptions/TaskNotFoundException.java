package com.mello.todoappcore.task.exceptions;

import java.util.UUID;

/**
 * Exceção lançada quando uma Task não é encontrada pelo ID fornecido.
 * Representa violação de uma regra de aplicação (entidade não existe).
 */
public class TaskNotFoundException extends TaskDomainException {

    public TaskNotFoundException(UUID id) {
        super(String.format("Task com ID %s não foi encontrada", id));
    }
}
