package com.mello.todoappcore.task.usecases;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.domain.enums.TaskStatus;
import com.mello.todoappcore.task.domain.vo.Description;
import com.mello.todoappcore.task.domain.vo.Title;
import com.mello.todoappcore.task.ports.ITaskRepository;
import com.mello.todoappcore.task.usecases.impl.CreateTaskUseCaseImpl;
import com.mello.todoappcore.task.usecases.impl.ListTasksUseCaseImpl;
import com.mello.todoappcore.task.usecases.impl.UpdateTaskUseCaseImpl;
import com.mello.todoappcore.task.usecases.repository.FakeTaskRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

@DisplayName("List Task Use Case Test")
class ListTasksUseCaseTest {

    private static final String TEST_DESCRIPTION = "Test description";

    private ITaskRepository taskRepository;
    private ICreateTaskUseCase createTaskUseCase;
    private IListTasksUseCase listTasksUseCase;
    private IUpdateTaskUseCase updateTaskUseCase;

    @BeforeEach
    void setUp() {
        taskRepository = new FakeTaskRepository();
        createTaskUseCase = new CreateTaskUseCaseImpl(taskRepository);
        listTasksUseCase = new ListTasksUseCaseImpl(taskRepository);
        updateTaskUseCase = new UpdateTaskUseCaseImpl(taskRepository);
    }

    @Test
    @DisplayName("Should list all tasks successfully")
    void shouldListAllTasks() {
        /* Given */
        Task task1 = new Task(
                UUID.randomUUID(),
                new Title("Task Test 1"),
                new Description(TEST_DESCRIPTION)
        );

        Task task2 = new Task(
                UUID.randomUUID(),
                new Title("Task Test 2"),
                new Description(TEST_DESCRIPTION)
        );

        Task task3 = new Task(
                UUID.randomUUID(),
                new Title("Task Test 3"),
                new Description(TEST_DESCRIPTION)
        );

        createTaskUseCase.execute(task1);
        createTaskUseCase.execute(task2);
        createTaskUseCase.execute(task3);

        /* When */
        List<Task> allTasks = listTasksUseCase.execute();

        /* Then */
        Assertions.assertNotNull(allTasks);
        Assertions.assertEquals(3, allTasks.size());
    }

    @Test
    @DisplayName("Should return empty list when no tasks exist")
    void shouldReturnEmptyListWhenNoTasks() {
        /* When */
        List<Task> allTasks = listTasksUseCase.execute();

        /* Then */
        Assertions.assertNotNull(allTasks);
        Assertions.assertTrue(allTasks.isEmpty());
    }

    @Test
    @DisplayName("Should return only enabled tasks")
    void shouldReturnOnlyEnabledTasks() {
        /* Given */
        Task enabledTask1 = new Task(
                UUID.randomUUID(),
                new Title("Enabled 1"),
                new Description(TEST_DESCRIPTION)
        );

        Task enabledTask2 = new Task(
                UUID.randomUUID(),
                new Title("Enabled 2"),
                new Description(TEST_DESCRIPTION)
        );

        Task disabledTask = new Task(
                UUID.randomUUID(),
                new Title("Disabled"),
                new Description(TEST_DESCRIPTION)
        );

        createTaskUseCase.execute(enabledTask1);
        createTaskUseCase.execute(enabledTask2);
        createTaskUseCase.execute(disabledTask);

        updateTaskUseCase.execute(
            disabledTask.getId(),
            disabledTask.getTitle(),
            disabledTask.getDescription(),
            TaskStatus.CANCELED
        );

        // Mark one task as deleted (disabled)
        disabledTask.markAsDeleted();
        taskRepository.saveTask(disabledTask);

        /* When */
        List<Task> allTasks = listTasksUseCase.execute();

        /* Then */
        Assertions.assertNotNull(allTasks);
        Assertions.assertEquals(2, allTasks.size());
        Assertions.assertTrue(allTasks.stream().allMatch(Task::isEnabled));
    }

}
