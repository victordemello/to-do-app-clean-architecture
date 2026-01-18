package com.mello.todoappinfra.task.rest.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Response body after creating a task")
public record CreateTaskResponseDto(
        @Schema(description = "Unique identifier of the created task", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Title of the created task", example = "Complete project documentation")
        String title,

        @Schema(description = "Description of the created task", example = "Write comprehensive documentation for the API endpoints")
        String description,

        @Schema(description = "Current status of the task", example = "BACKLOG")
        String status
) {
}
