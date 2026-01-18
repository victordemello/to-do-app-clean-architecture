package com.mello.todoappquarkus.task.rest.dto.output;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Response body containing task details")
public record GetTaskResponseDto(
        @Schema(description = "Unique identifier of the task", example = "550e8400-e29b-41d4-a716-446655440000")
        UUID id,

        @Schema(description = "Title of the task", example = "Complete project documentation")
        String title,

        @Schema(description = "Description of the task", example = "Write comprehensive documentation for the API endpoints")
        String description,

        @Schema(description = "Current status of the task", example = "IN_PROGRESS")
        String status
) {
}
