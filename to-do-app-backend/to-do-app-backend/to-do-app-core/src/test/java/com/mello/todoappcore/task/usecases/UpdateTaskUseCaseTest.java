package com.mello.todoappcore.task.usecases;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.exceptions.InvalidStatusTransitionException;
import com.mello.todoappcore.task.exceptions.TaskImmutableException;
import com.mello.todoappcore.task.exceptions.TaskNotFoundException;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.impl.CreateTaskUseCaseImpl;
import com.mello.todoappcore.task.usecases.impl.UpdateTaskUseCaseImpl;
import com.mello.todoappcore.task.usecases.repository.FakeTaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@DisplayName("Update Task Use Case Test")
class UpdateTaskUseCaseTest {

    private static final UUID TASK_ID = UUID.fromString("11111111-1111-1111-1111-111111111111");
    private static final String TEST_DESCRIPTION = "Test description";

    private ITaskRepository taskRepository;
    private ICreateTaskUseCase createTaskUseCase;
    private IUpdateTaskUseCase updateTaskUseCase;

    @BeforeEach
    void setUp() {
        taskRepository = new FakeTaskRepository();
        createTaskUseCase = new CreateTaskUseCaseImpl(taskRepository);
        updateTaskUseCase = new UpdateTaskUseCaseImpl(taskRepository);
    }

    // ==================== Happy Path Tests ====================

    @Test
    @DisplayName("Should update task details successfully")
    void shouldUpdateTaskDetailsSuccessfully() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Original Title"), new Description(TEST_DESCRIPTION));
        createTaskUseCase.execute(task);

        /* When */
        Task updatedTask = updateTaskUseCase.execute(
                TASK_ID,
                new Title("Updated Title"),
                new Description("Updated description"),
                TaskStatus.BACKLOG
        );

        /* Then */
        Assertions.assertNotNull(updatedTask);
        Assertions.assertEquals(TASK_ID, updatedTask.getId());
        Assertions.assertEquals("Updated Title", updatedTask.getTitle().value());
        Assertions.assertEquals("Updated description", updatedTask.getDescription().value());
        Assertions.assertEquals(TaskStatus.BACKLOG, updatedTask.getStatus());
    }

    @Test
    @DisplayName("Should update task status successfully")
    void shouldUpdateTaskStatusSuccessfully() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Task Title"), new Description(TEST_DESCRIPTION));
        createTaskUseCase.execute(task);

        /* When */
        Task updatedTask = updateTaskUseCase.execute(
                TASK_ID,
                new Title("Task Title"),
                new Description(TEST_DESCRIPTION),
                TaskStatus.IN_PROGRESS
        );

        /* Then */
        Assertions.assertNotNull(updatedTask);
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());
    }

    @Test
    @DisplayName("Should update title, description and status together")
    void shouldUpdateTitleDescriptionAndStatusTogether() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Original"), new Description("Original desc"));
        createTaskUseCase.execute(task);

        /* When */
        Task updatedTask = updateTaskUseCase.execute(
                TASK_ID,
                new Title("New Title"),
                new Description("New description"),
                TaskStatus.IN_PROGRESS
        );

        /* Then */
        Assertions.assertNotNull(updatedTask);
        Assertions.assertEquals("New Title", updatedTask.getTitle().value());
        Assertions.assertEquals("New description", updatedTask.getDescription().value());
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());
    }

    // ==================== Error Tests ====================

    @Test
    @DisplayName("Should throw exception when task ID is null")
    void shouldThrowExceptionWhenTaskIdIsNull() {
        /* When & Then */
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> updateTaskUseCase.execute(
                        null,
                        new Title("Title"),
                        new Description(TEST_DESCRIPTION),
                        TaskStatus.BACKLOG
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when task not found")
    void shouldThrowExceptionWhenTaskNotFound() {
        /* Given */
        UUID nonExistentId = UUID.randomUUID();

        /* When & Then */
        TaskNotFoundException exception = Assertions.assertThrows(
                TaskNotFoundException.class,
                () -> updateTaskUseCase.execute(
                        nonExistentId,
                        new Title("Title"),
                        new Description(TEST_DESCRIPTION),
                        TaskStatus.BACKLOG
                )
        );

        Assertions.assertTrue(exception.getMessage().contains(nonExistentId.toString()));
    }

    // ==================== Status Transition Tests - Valid ====================

    @Test
    @DisplayName("Should allow valid status transition from BACKLOG to IN_PROGRESS")
    void shouldAllowValidStatusTransitionFromBacklogToInProgress() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Task"), new Description(TEST_DESCRIPTION));
        createTaskUseCase.execute(task);

        /* When */
        Task updatedTask = updateTaskUseCase.execute(
                TASK_ID,
                new Title("Task"),
                new Description(TEST_DESCRIPTION),
                TaskStatus.IN_PROGRESS
        );

        /* Then */
        Assertions.assertEquals(TaskStatus.IN_PROGRESS, updatedTask.getStatus());
    }

    @Test
    @DisplayName("Should allow valid status transition from BACKLOG to CANCELED")
    void shouldAllowValidStatusTransitionFromBacklogToCanceled() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Task"), new Description(TEST_DESCRIPTION));
        createTaskUseCase.execute(task);

        /* When */
        Task updatedTask = updateTaskUseCase.execute(
                TASK_ID,
                new Title("Task"),
                new Description(TEST_DESCRIPTION),
                TaskStatus.CANCELED
        );

        /* Then */
        Assertions.assertEquals(TaskStatus.CANCELED, updatedTask.getStatus());
    }

    @Test
    @DisplayName("Should allow valid status transition from IN_PROGRESS to DONE")
    void shouldAllowValidStatusTransitionFromInProgressToDone() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Task"), new Description(TEST_DESCRIPTION));
        createTaskUseCase.execute(task);

        // First transition: BACKLOG -> IN_PROGRESS
        updateTaskUseCase.execute(
                TASK_ID,
                new Title("Task"),
                new Description(TEST_DESCRIPTION),
                TaskStatus.IN_PROGRESS
        );

        /* When */
        Task updatedTask = updateTaskUseCase.execute(
                TASK_ID,
                new Title("Task"),
                new Description(TEST_DESCRIPTION),
                TaskStatus.DONE
        );

        /* Then */
        Assertions.assertEquals(TaskStatus.DONE, updatedTask.getStatus());
    }

    // ==================== Status Transition Tests - Invalid ====================

    @Test
    @DisplayName("Should throw exception for invalid status transition from BACKLOG to DONE")
    void shouldThrowExceptionForInvalidStatusTransition() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Task"), new Description(TEST_DESCRIPTION));
        createTaskUseCase.execute(task);

        /* When & Then */
        Assertions.assertThrows(
                InvalidStatusTransitionException.class,
                () -> updateTaskUseCase.execute(
                        TASK_ID,
                        new Title("Task"),
                        new Description(TEST_DESCRIPTION),
                        TaskStatus.DONE
                )
        );
    }

    @Test
    @DisplayName("Should throw exception when transitioning from final state DONE")
    void shouldThrowExceptionWhenTransitioningFromFinalState() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Task"), new Description(TEST_DESCRIPTION));
        createTaskUseCase.execute(task);

        // Transition to DONE: BACKLOG -> IN_PROGRESS -> DONE
        updateTaskUseCase.execute(TASK_ID, new Title("Task"), new Description(TEST_DESCRIPTION), TaskStatus.IN_PROGRESS);
        updateTaskUseCase.execute(TASK_ID, new Title("Task"), new Description(TEST_DESCRIPTION), TaskStatus.DONE);

        /* When & Then */
        Assertions.assertThrows(
                TaskImmutableException.class,
                () -> updateTaskUseCase.execute(
                        TASK_ID,
                        new Title("Task"),
                        new Description(TEST_DESCRIPTION),
                        TaskStatus.IN_PROGRESS
                )
        );
    }

    // ==================== Edge Case Tests ====================

    @Test
    @DisplayName("Should not update status when same status provided")
    void shouldNotUpdateStatusWhenSameStatusProvided() {
        /* Given */
        Task task = new Task(TASK_ID, new Title("Task"), new Description(TEST_DESCRIPTION));
        createTaskUseCase.execute(task);

        /* When */
        Task updatedTask = updateTaskUseCase.execute(
                TASK_ID,
                new Title("Updated Title"),
                new Description("Updated description"),
                TaskStatus.BACKLOG
        );

        /* Then */
        Assertions.assertEquals(TaskStatus.BACKLOG, updatedTask.getStatus());
        Assertions.assertEquals("Updated Title", updatedTask.getTitle().value());
        Assertions.assertEquals("Updated description", updatedTask.getDescription().value());
    }
}
