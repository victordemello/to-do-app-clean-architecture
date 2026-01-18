package com.mello.todoappcore.task.usecases.repository;

import com.mello.todoappcore.task.domain.entities.Task;
import com.mello.todoappcore.task.ports.ITaskRepository;

import java.util.*;
import java.util.stream.Collectors;

public class FakeTaskRepository implements ITaskRepository {

    private final Map<UUID, Task> db = new HashMap<>();

    public FakeTaskRepository() {

    }

    public FakeTaskRepository(List<Task> initialTasks) {
        for (Task task : initialTasks) {
            db.put(task.getId(), task);
        }
    }

    @Override
    public Task saveTask(Task task) {
        db.put(task.getId(), task);
        return task;
    }

    @Override
    public Optional<Task> findActiveById(UUID taskId) {
        return Optional.ofNullable(db.get(taskId)).filter(Task::isEnabled);
    }

    @Override
    public List<Task> findAllActive() {
        return db.values().stream()
                .filter(Task::isEnabled)
                .collect(Collectors.toList());
    }
}
