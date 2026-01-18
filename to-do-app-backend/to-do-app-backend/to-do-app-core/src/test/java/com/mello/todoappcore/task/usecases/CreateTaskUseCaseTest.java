package com.mello.todoappcore.task.usecases;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.impl.CreateTaskUseCaseImpl;
import com.mello.todoappcore.task.usecases.repository.FakeTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Create Task Use Case")
class CreateTaskUseCaseTest {

    private ITaskRepository taskRepository;
    private ICreateTaskUseCase createTaskUseCase;

    @BeforeEach
    void init() {
        taskRepository = new FakeTaskRepository();
        createTaskUseCase = new CreateTaskUseCaseImpl(taskRepository);
    }

    @Test
    @DisplayName("Should create and persist a task successfully")
    void shouldCreateAndPersistTask() {
        // Arrange (Given)
        Task taskToCreate = new Task(
                UUID.randomUUID(),
                new Title("Task test"),
                new Description("Test Description")
        );

        // Act (When)
        createTaskUseCase.execute(taskToCreate);

        // Assert (Then)
        Optional<Task> savedTaskOptional = taskRepository.findActiveById(taskToCreate.getId());
        assertTrue(savedTaskOptional.isPresent(), "Task should be found in repository");

        Task savedTask = savedTaskOptional.get();
        assertEquals(taskToCreate.getId(), savedTask.getId());
        assertEquals("Task test", savedTask.getTitle().value());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when task is null")
    void shouldThrowExceptionWhenTaskIsNull(){
        // Arrange
        Task task = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            createTaskUseCase.execute(task);
        });

        assertEquals("Task cannot be null", exception.getMessage());
    }
}
