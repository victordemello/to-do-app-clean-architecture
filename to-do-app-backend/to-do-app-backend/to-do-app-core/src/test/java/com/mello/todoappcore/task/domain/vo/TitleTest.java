package com.mello.todoappcore.task.domain.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Title Value Object")
class TitleTest {

    @Test
    @DisplayName("Should create title successfully")
    void shouldCreateTitleSuccessfully() {
        Title title = new Title("A valid title");
        assertEquals("A valid title", title.value());
    }

    @Test
    @DisplayName("Should throw exception for null title")
    void shouldThrowExceptionForNullTitle() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Title(null));
        assertEquals("Title cannot be null or blank", ex.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for blank title")
    void shouldThrowExceptionForBlankTitle() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Title("   "));
        assertEquals("Title cannot be null or blank", ex.getMessage());
    }

    @Test
    @DisplayName("Should throw exception for title exceeding max length")
    void shouldThrowExceptionForTitleExceedingMaxLength() {
        // Create a string with 101 characters
        String longTitle = "a".repeat(101);
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Title(longTitle));
        assertEquals("Title cannot exceed 100 characters", ex.getMessage());
    }
}
