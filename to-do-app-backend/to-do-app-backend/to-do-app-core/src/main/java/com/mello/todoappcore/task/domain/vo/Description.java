package com.mello.todoappcore.task.domain.vo;

public record Description(
        String value
) {
    public Description {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null");
        }
        if (value.length() > 500) {
            throw new IllegalArgumentException("Description cannot exceed 500 characters");
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
