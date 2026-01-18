package com.mello.todoappcore.task.exceptions;

/**
 * Exceção base para todas as exceções de domínio relacionadas a Task.
 * Seguindo o princípio Open/Closed do SOLID, esta classe permite extensão
 * via herança para exceções específicas do domínio.
 */
public class TaskDomainException extends RuntimeException {

    public TaskDomainException(String message) {
        super(message);
    }

    public TaskDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
