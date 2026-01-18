package com.mello.todoappinfra.task.rest.dto.input;

import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record DeleteTaskRequestDto(
        @NotBlank(message = "Id is required")
        UUID id
) {
}
