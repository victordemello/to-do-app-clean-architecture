package com.mello.todoappquarkus.configuration;

import com.mello.todoappcore.task.exceptions.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.time.LocalDateTime;

/**
 * Global exception handlers para mapear exceções de domínio em respostas HTTP.
 * Equivalente ao GlobalExceptionHandler do Spring, mas usando JAX-RS ExceptionMapper.
 * Cada exceção tem seu próprio mapper com @Provider.
 */
public class GlobalExceptionHandler {

    @Provider
    public static class TaskNotFoundExceptionMapper
            implements ExceptionMapper<TaskNotFoundException> {
        @Override
        public Response toResponse(TaskNotFoundException ex) {
            ErrorResponse error = new ErrorResponse(
                    Response.Status.NOT_FOUND.getStatusCode(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
    }

    @Provider
    public static class TaskAlreadyDeletedExceptionMapper
            implements ExceptionMapper<TaskAlreadyDeletedException> {
        @Override
        public Response toResponse(TaskAlreadyDeletedException ex) {
            ErrorResponse error = new ErrorResponse(
                    410,  // HTTP 410 GONE
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return Response.status(410).entity(error).build();
        }
    }

    @Provider
    public static class InvalidStatusTransitionExceptionMapper
            implements ExceptionMapper<InvalidStatusTransitionException> {
        @Override
        public Response toResponse(InvalidStatusTransitionException ex) {
            ErrorResponse error = new ErrorResponse(
                    Response.Status.BAD_REQUEST.getStatusCode(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @Provider
    public static class CannotDeleteNonCanceledTaskExceptionMapper
            implements ExceptionMapper<CannotDeleteNonCanceledTaskException> {
        @Override
        public Response toResponse(CannotDeleteNonCanceledTaskException ex) {
            ErrorResponse error = new ErrorResponse(
                    Response.Status.BAD_REQUEST.getStatusCode(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @Provider
    public static class TaskImmutableExceptionMapper
            implements ExceptionMapper<TaskImmutableException> {
        @Override
        public Response toResponse(TaskImmutableException ex) {
            ErrorResponse error = new ErrorResponse(
                    Response.Status.BAD_REQUEST.getStatusCode(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    @Provider
    public static class IllegalArgumentExceptionMapper
            implements ExceptionMapper<IllegalArgumentException> {
        @Override
        public Response toResponse(IllegalArgumentException ex) {
            ErrorResponse error = new ErrorResponse(
                    Response.Status.BAD_REQUEST.getStatusCode(),
                    ex.getMessage(),
                    LocalDateTime.now()
            );
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }
    }

    /**
     * Record para formato de resposta de erro.
     * Mantém mesma estrutura do Spring para compatibilidade de API.
     */
    public record ErrorResponse(
            int status,
            String message,
            LocalDateTime timestamp
    ) {}
}
