package com.mello.todoappinfra.task.rest.dto.input;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Request body for creating a new task")
public record CreateTaskRequestDto(
        @Schema(description = "Title of the task", example = "Complete project documentation", maxLength = 100)
        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title cannot exceed 100 characters")
        String title,

        @Schema(description = "Detailed description of the task", example = "Write comprehensive documentation for the API endpoints", maxLength = 500)
        @NotNull(message = "Description cannot be null")
        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description
) {
}
