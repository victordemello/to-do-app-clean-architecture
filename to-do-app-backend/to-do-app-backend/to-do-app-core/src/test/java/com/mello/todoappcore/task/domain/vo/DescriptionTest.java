package com.mello.todoappcore.task.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Description Value Object")
class DescriptionTest {

    @Test
    @DisplayName("Should create description successfully")
    void shouldCreateDescriptionSuccessfully() {
        Description description = new Description("A valid description.");
        assertEquals("A valid description.", description.value());
    }

    @Test
    @DisplayName("Should throw exception for null description")
    void shouldThrowExceptionForNullDescription() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Description(null));
        assertEquals("Description cannot be null", ex.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for blank description")
    void shouldThrowExceptionForBlankDescription() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Description("   "));
        assertEquals("Description cannot be null", ex.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for description exceeding max length")
    void shouldThrowExceptionForDescriptionExceedingMaxLength() {
        // Create a string with 501 characters
        String longDescription = "a".repeat(501);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Description(longDescription));
        assertEquals("Description cannot exceed 500 characters", ex.getMessage());
    }
}
