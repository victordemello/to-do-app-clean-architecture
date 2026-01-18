package com.mello.todoappinfra.task.rest.dto.output;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Response body after updating a task")
public record UpdateTaskResponseDto(
        @Schema(description = "Unique identifier of the updated task", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Updated title of the task", example = "Complete project documentation")
        String title,

        @Schema(description = "Updated description of the task", example = "Write comprehensive documentation for the API endpoints")
        String description,

        @Schema(description = "Updated status of the task", example = "IN_PROGRESS")
        String status
) {
}
