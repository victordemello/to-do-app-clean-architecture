package com.mello.todoappcore.task.usecases;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.exceptions.TaskNotFoundException;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.impl.CreateTaskUseCaseImpl;
import com.mello.todoappcore.task.usecases.impl.GetTaskUseCaseImpl;
import com.mello.todoappcore.task.usecases.repository.FakeTaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@DisplayName("Get Task Use Case")
class GetTaskUseCaseTest {

    private static final UUID TASK_ID = UUID.fromString("68cf29ef-58a7-4e28-b30f-f610de2ef017");

    private ITaskRepository taskRepository;
    private IGetTaskUseCase getTaskUseCase;
    private ICreateTaskUseCase createTaskUseCase;

    @BeforeEach
    void init(){
        taskRepository = new FakeTaskRepository();
        getTaskUseCase = new GetTaskUseCaseImpl(taskRepository);
        createTaskUseCase = new CreateTaskUseCaseImpl(taskRepository);
    }

    @Test
    @DisplayName("Should get task by ID successfully")
    void shouldGetTaskById(){
        /* Given */
        Task task = new Task(
                TASK_ID,
                new Title("Task Test"),
                new Description("Task created for unit testing")
        );

        createTaskUseCase.execute(task);

        /* When */
        Task taskCreated = getTaskUseCase.execute(TASK_ID);

        /* Then */
        Assertions.assertNotNull(taskCreated);
        Assertions.assertEquals(TASK_ID, taskCreated.getId());
    }

    @Test
    @DisplayName("Should throw TaskNotFoundException when task does not exist")
    void shouldThrowExceptionWhenTaskNotFound() {
        /* Given */
        UUID nonExistentId = UUID.randomUUID();

        /* When & Then */
        TaskNotFoundException exception = Assertions.assertThrows(
                TaskNotFoundException.class,
                () -> getTaskUseCase.execute(nonExistentId)
        );

        Assertions.assertTrue(exception.getMessage().contains(nonExistentId.toString()));
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when task ID is null")
    void shouldThrowExceptionWhenTaskIdIsNull() {
        /* When & Then */
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> getTaskUseCase.execute(null)
        );
    }

}
