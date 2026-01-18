package com.mello.todoappquarkus.task.rest.dto.input;

import jakarta.validation.constraints.NotBlank;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Schema(description = "Request body for updating an existing task")
public record UpdateTaskRequestDto(
        @Schema(description = "Updated title of the task", example = "Complete project documentation")
        @NotBlank(message = "Title cannot be blank")
        String title,

        @Schema(description = "Updated description of the task", example = "Write comprehensive documentation for the API endpoints")
        @NotBlank(message = "Description cannot be blank")
        String description,

        @Schema(description = "Updated status of the task", example = "IN_PROGRESS", enumeration = {"BACKLOG", "IN_PROGRESS", "DONE"})
        @NotBlank(message = "Status cannot be blank")
        String status
) {
}
