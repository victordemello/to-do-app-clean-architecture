package com.mello.todoappquarkus.task.rest.controllers;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.usecases.*;
import com.mello.todoappquarkus.task.mappers.TaskRequestMapper;
import com.mello.todoappquarkus.task.mappers.TaskResponseMapper;
import com.mello.todoappquarkus.task.rest.dto.input.CreateTaskRequestDto;
import com.mello.todoappquarkus.task.rest.dto.input.UpdateTaskRequestDto;
import com.mello.todoappquarkus.task.rest.dto.output.*;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/api/task")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Tasks", description = "Operations for managing tasks")
public class TaskResource {

    @Inject
    ICreateTaskUseCase createTaskUseCase;

    @Inject
    IGetTaskUseCase getTaskUseCase;

    @Inject
    IUpdateTaskUseCase updateTaskUseCase;

    @Inject
    IDeleteTaskUseCase deleteTaskUseCase;

    @Inject
    IListTasksUseCase listTasksUseCase;

    @POST
    @Path("/create")
    @Operation(summary = "Create a new task", description = "Creates a new task with the provided title and description")
    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Task created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CreateTaskResponseDto.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response createTask(@Valid CreateTaskRequestDto request) {
        Task task = TaskRequestMapper.toTask(request);
        Task createdTask = createTaskUseCase.execute(task);
        CreateTaskResponseDto response = TaskResponseMapper.toCreateDto(createdTask);

        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get task by ID", description = "Retrieves a task by its unique identifier")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Task found",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetTaskResponseDto.class))),
            @APIResponse(responseCode = "404", description = "Task not found")
    })
    public Response getTaskById(
            @Parameter(description = "UUID of the task to retrieve", required = true)
            @PathParam("id") UUID taskId) {
        Task task = getTaskUseCase.execute(taskId);
        GetTaskResponseDto response = TaskResponseMapper.toGetDto(task);

        return Response.ok(response).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a task", description = "Updates an existing task with new title, description, and status")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Task updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateTaskResponseDto.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data"),
            @APIResponse(responseCode = "404", description = "Task not found")
    })
    public Response updateTask(
            @Parameter(description = "UUID of the task to update", required = true)
            @PathParam("id") UUID taskId,
            @Valid UpdateTaskRequestDto request) {
        Title title = TaskRequestMapper.toTitle(request.title());
        Description description = TaskRequestMapper.toDescription(request.description());
        TaskStatus status = TaskRequestMapper.toTaskStatus(request.status());

        Task updatedTask = updateTaskUseCase.execute(taskId, title, description, status);
        UpdateTaskResponseDto response = TaskResponseMapper.toUpdateDto(updatedTask);

        return Response.ok(response).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a task", description = "Deletes a task by its unique identifier")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Task deleted successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = DeleteTaskResponseDto.class))),
            @APIResponse(responseCode = "404", description = "Task not found")
    })
    public Response deleteTask(
            @Parameter(description = "UUID of the task to delete", required = true)
            @PathParam("id") UUID taskId) {
        Task task = getTaskUseCase.execute(taskId);
        deleteTaskUseCase.execute(taskId);
        DeleteTaskResponseDto response = TaskResponseMapper.toDeleteDto(task);

        return Response.ok(response).build();
    }

    @GET
    @Path("/all")
    @Operation(summary = "List all tasks", description = "Retrieves all tasks in the system")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Tasks retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = GetTaskResponseDto.class)))
    })
    public Response listTasks() {
        List<Task> tasks = listTasksUseCase.execute();
        List<GetTaskResponseDto> response = TaskResponseMapper.toListDto(tasks);

        return Response.ok(response).build();
    }
}
