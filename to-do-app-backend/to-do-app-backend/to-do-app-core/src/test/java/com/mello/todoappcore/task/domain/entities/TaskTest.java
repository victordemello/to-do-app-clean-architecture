package com.mello.todoappcore.task.domain.entities;

import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.exceptions.CannotDeleteNonCanceledTaskException;
import com.mello.todoappcore.task.exceptions.InvalidStatusTransitionException;
import com.mello.todoappcore.task.exceptions.TaskAlreadyDeletedException;
import com.mello.todoappcore.task.exceptions.TaskImmutableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task Entity Business Rules")
class TaskTest {

    private Title defaultTitle;
    private Description defaultDescription;

    @BeforeEach
    void setUp() {
        defaultTitle = new Title("Default Title");
        defaultDescription = new Description("Default description.");
    }

    @Test
    @DisplayName("Should create task with default status BACKLOG")
    void shouldCreateTaskWithDefaultValues() {
        // when
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription);

        // then
        assertEquals(TaskStatus.BACKLOG, task.getStatus());
        assertTrue(task.isEnabled());
        assertNotNull(task.getCreatedAt());
        assertNotNull(task.getUpdatedAt());
    }

    @Test
    @DisplayName("Should throw exception when creating task with null title")
    void shouldThrowExceptionWhenCreatingTaskWithNullTitle() {
        // when / then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            new Task(UUID.randomUUID(), null, defaultDescription);
        });
        assertEquals("Title cannot be null", ex.getMessage());
    }

    @Test
    @DisplayName("Should throw exception when creating task with null description")
    void shouldThrowExceptionWhenCreatingTaskWithNullDescription() {
        // when / then
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            new Task(UUID.randomUUID(), defaultTitle, null);
        });
        assertEquals("Description cannot be null", ex.getMessage());
    }

    @Test
    @DisplayName("Should update status when transition is valid")
    void shouldUpdateStatusWhenTransitionIsValid() {
        // given
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription);

        // when
        task.updateStatus(TaskStatus.IN_PROGRESS);

        // then
        assertEquals(TaskStatus.IN_PROGRESS, task.getStatus());
    }

    @Test
    @DisplayName("Should throw exception for invalid status transition")
    void shouldThrowExceptionWhenTransitionIsInvalid() {
        // given
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription);

        // when / then
        assertThrows(InvalidStatusTransitionException.class, () -> {
            task.updateStatus(TaskStatus.DONE); // BACKLOG -> DONE is not allowed
        });
    }

    @Test
    @DisplayName("Should throw exception when updating status of a finished task")
    void shouldThrowExceptionWhenUpdatingStatusOfFinishedTask() {
        // given
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription, TaskStatus.DONE, true, LocalDateTime.now(), LocalDateTime.now());

        // when / then
        assertThrows(TaskImmutableException.class, () -> {
            task.updateStatus(TaskStatus.IN_PROGRESS);
        });
    }

    @Test
    @DisplayName("Should update details successfully for a non-finished task")
    void shouldUpdateDetailsSuccessfully() {
        // given
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription);
        Title newTitle = new Title("New Title");
        Description newDescription = new Description("New Description");

        // when
        task.updateDetails(newTitle, newDescription);

        // then
        assertEquals(newTitle, task.getTitle());
        assertEquals(newDescription, task.getDescription());
    }

    @Test
    @DisplayName("Should throw exception when updating details of a finished task")
    void shouldThrowExceptionWhenUpdatingDetailsOfFinishedTask() {
        // given
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription, TaskStatus.CANCELED, true, LocalDateTime.now(), LocalDateTime.now());

        // when / then
        assertThrows(TaskImmutableException.class, () -> {
            task.updateDetails(new Title("Another Title"), new Description("Another desc"));
        });
    }

    @Test
    @DisplayName("Should mark as deleted when status is CANCELED")
    void shouldMarkAsDeletedWhenStatusIsCanceled() {
        // given
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription, TaskStatus.CANCELED, true, LocalDateTime.now(), LocalDateTime.now());

        // when
        task.markAsDeleted();

        // then
        assertFalse(task.isEnabled());
    }

    @Test
    @DisplayName("Should throw exception when deleting a non-canceled task")
    void shouldThrowExceptionWhenDeletingNonCanceledTask() {
        // given
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription, TaskStatus.IN_PROGRESS, true, LocalDateTime.now(), LocalDateTime.now());

        // when / then
        assertThrows(CannotDeleteNonCanceledTaskException.class, task::markAsDeleted);
    }

    @Test
    @DisplayName("Should throw exception when modifying a deleted task")
    void shouldThrowExceptionWhenModifyingADeletedTask() {
        // given
        Task task = new Task(UUID.randomUUID(), defaultTitle, defaultDescription, TaskStatus.CANCELED, false, LocalDateTime.now(), LocalDateTime.now());

        // when / then
        assertThrows(TaskAlreadyDeletedException.class, () -> {
            task.updateStatus(TaskStatus.IN_PROGRESS);
        });

        assertThrows(TaskAlreadyDeletedException.class, () -> {
            task.updateDetails(new Title("..."), new Description("..."));
        });

        assertThrows(TaskAlreadyDeletedException.class, task::markAsDeleted);
    }
}