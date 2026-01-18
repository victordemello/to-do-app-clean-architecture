package com.mello.todoappcore.task.exceptions;

import com.mello.todoappcore.task.domain.enums.TaskStatus;

import java.util.UUID;

/**
 * Exceção lançada quando tenta-se modificar uma Task imutável.
 * Tasks com status DONE ou CANCELED são imutáveis e não podem ter
 * seus campos (título, descrição, status) alterados.
 */
public class TaskImmutableException extends TaskDomainException {

    public TaskImmutableException(UUID id, TaskStatus status) {
        super(String.format(
                "Task %s com status %s é imutável e não pode ser modificada",
                id, status
        ));
    }
}
