package com.mello.todoappcore.task.domain.vo;

public record Title(
        String value
) {
    public Title {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null or blank");
        }
        if (value.length() > 100) {
            throw new IllegalArgumentException("Title cannot exceed 100 characters");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
