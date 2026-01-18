package com.mello.todoappinfra.task.rest.controllers;

import com.mello.todoappinfra.task.rest.dto.input.CreateTaskRequestDto;
import com.mello.todoappinfra.task.rest.dto.input.UpdateTaskRequestDto;
import com.mello.todoappinfra.task.rest.dto.output.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

@Tag(name = "Tasks", description = "Operations for managing tasks")
public interface ITaskController {

    @Operation(summary = "Create a new task", description = "Creates a new task with the provided title and description")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateTaskResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content)
    })
    ResponseEntity<CreateTaskResponseDto> createTask(CreateTaskRequestDto createTaskRequestDto);

    @Operation(summary = "Get task by ID", description = "Retrieves a task by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetTaskResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    ResponseEntity<GetTaskResponseDto> getTaskById(
            @Parameter(description = "UUID of the task to retrieve", required = true) UUID taskId);

    @Operation(summary = "Update a task", description = "Updates an existing task with new title, description, and status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateTaskResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    ResponseEntity<UpdateTaskResponseDto> updateTask(
            @Parameter(description = "UUID of the task to update", required = true) UUID taskId,
            UpdateTaskRequestDto updateTaskRequestDto);

    @Operation(summary = "Delete a task", description = "Deletes a task by its unique identifier")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteTaskResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "Task not found", content = @Content)
    })
    ResponseEntity<DeleteTaskResponseDto> deleteTask(
            @Parameter(description = "UUID of the task to delete", required = true) UUID taskId);

    @Operation(summary = "List all tasks", description = "Retrieves all tasks in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetTaskResponseDto.class)))
    })
    ResponseEntity<List<GetTaskResponseDto>> listTasks();
}
