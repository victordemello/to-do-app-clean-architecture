package com.mello.todoappcore.task.usecases;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.exceptions.TaskNotFoundException;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.impl.DeleteTaskUseCaseImpl;
import com.mello.todoappcore.task.usecases.repository.FakeTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Delete Task Use Case")
class DeleteTaskUseCaseTest {

    private ITaskRepository taskRepository;
    private IDeleteTaskUseCase deleteTaskUseCase;
    private final UUID taskToDeleteId = UUID.fromString("aac33c07-821d-43b3-ae97-c5fea98d161e");

    @BeforeEach
    void init() {
        List<Task> tasks = List.of(
                new Task(taskToDeleteId,
                        new Title("Write unit tests"),
                        new Description("Create unit tests for the task use cases")
                ),
                new Task(UUID.fromString("ddebe484-5af7-43b8-bc4f-2b19c2d107b8"),
                        new Title("Refactor service layer"),
                        new Description("Improve code organization and readability")
                )
        );
        taskRepository = new FakeTaskRepository(tasks);
        deleteTaskUseCase = new DeleteTaskUseCaseImpl(taskRepository);
    }

    @Test
    @DisplayName("Should soft delete a task when its status is CANCELED")
    void shouldSoftDeleteTaskWhenStatusIsCanceled() {
        // Given (Arrange)
        Task task = taskRepository.findActiveById(taskToDeleteId)
                .orElseThrow(() -> new IllegalStateException("Task should exist for this test"));
        task.updateStatus(TaskStatus.CANCELED);
        taskRepository.saveTask(task); // Ensure the CANCELED status is persisted before acting

        // When (Act)
        deleteTaskUseCase.execute(taskToDeleteId);

        // Then (Assert)
        Optional<Task> taskAfterDelete = taskRepository.findActiveById(taskToDeleteId);
        assertTrue(taskAfterDelete.isEmpty(), "Task should not be found as active after being deleted");
    }

    @Test
    @DisplayName("Should throw TaskNotFoundException when trying to delete a non-existent task")
    void shouldThrowTaskNotFoundException() {
        // Arrange
        UUID nonExistentTaskId = UUID.fromString("5a8b91bf-ac29-43f3-bc0c-2474bf55ce58");

        // Act & Assert
        assertThrows(TaskNotFoundException.class, () -> deleteTaskUseCase.execute(nonExistentTaskId));
    }
}
