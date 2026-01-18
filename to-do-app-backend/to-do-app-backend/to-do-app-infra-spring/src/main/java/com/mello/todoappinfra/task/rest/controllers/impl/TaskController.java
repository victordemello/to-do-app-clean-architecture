package com.mello.todoappinfra.task.rest.controllers.impl;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.usecases.*;
import com.mello.todoappinfra.task.mappers.TaskRequestMapper;
import com.mello.todoappinfra.task.mappers.TaskResponseMapper;
import com.mello.todoappinfra.task.rest.controllers.ITaskController;
import com.mello.todoappinfra.task.rest.dto.input.CreateTaskRequestDto;
import com.mello.todoappinfra.task.rest.dto.input.UpdateTaskRequestDto;
import com.mello.todoappinfra.task.rest.dto.output.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController implements ITaskController {

    private final ICreateTaskUseCase createTaskUseCase;
    private final IGetTaskUseCase getTaskUseCase;
    private final IUpdateTaskUseCase updateTaskUseCase;
    private final IDeleteTaskUseCase deleteTaskUseCase;
    private final IListTasksUseCase listTasksUseCase;

    public TaskController(
            ICreateTaskUseCase createTaskUseCase,
            IGetTaskUseCase getTaskUseCase,
            IUpdateTaskUseCase updateTaskUseCase,
            IDeleteTaskUseCase deleteTaskUseCase,
            IListTasksUseCase listTasksUseCase
    ) {
        this.createTaskUseCase = createTaskUseCase;
        this.getTaskUseCase = getTaskUseCase;
        this.updateTaskUseCase = updateTaskUseCase;
        this.deleteTaskUseCase = deleteTaskUseCase;
        this.listTasksUseCase = listTasksUseCase;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<CreateTaskResponseDto> createTask(
            @RequestBody @Valid CreateTaskRequestDto createTaskRequestDto
    ) {
        Task task = TaskRequestMapper.toTask(createTaskRequestDto);
        Task createdTask = createTaskUseCase.execute(task);
        CreateTaskResponseDto response = TaskResponseMapper.toCreateDto(createdTask);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GetTaskResponseDto> getTaskById(@PathVariable("id") UUID taskId) {
        Task task = getTaskUseCase.execute(taskId);
        GetTaskResponseDto response = TaskResponseMapper.toGetDto(task);

        return ResponseEntity.ok(response);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<UpdateTaskResponseDto> updateTask(
            @PathVariable("id") UUID taskId,
            @RequestBody @Valid UpdateTaskRequestDto updateTaskRequestDto
    ) {
        Title title = TaskRequestMapper.toTitle(updateTaskRequestDto.title());
        Description description = TaskRequestMapper.toDescription(updateTaskRequestDto.description());
        TaskStatus status = TaskRequestMapper.toTaskStatus(updateTaskRequestDto.status());

        Task updatedTask = updateTaskUseCase.execute(taskId, title, description, status);
        UpdateTaskResponseDto response = TaskResponseMapper.toUpdateDto(updatedTask);

        return ResponseEntity.ok(response);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<DeleteTaskResponseDto> deleteTask(@PathVariable("id") UUID taskId) {
        Task task = getTaskUseCase.execute(taskId);
        deleteTaskUseCase.execute(taskId);
        DeleteTaskResponseDto response = TaskResponseMapper.toDeleteDto(task);

        return ResponseEntity.ok(response);
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<GetTaskResponseDto>> listTasks() {
        List<Task> tasks = listTasksUseCase.execute();
        List<GetTaskResponseDto> response = TaskResponseMapper.toListDto(tasks);

        return ResponseEntity.ok(response);
    }
}