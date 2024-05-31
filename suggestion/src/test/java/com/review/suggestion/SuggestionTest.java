package com.review.suggestion;

import org.junit.jupiter.api.Test;

import com.review.suggestion.entity.Suggestion;

import static org.junit.jupiter.api.Assertions.*;

public class SuggestionTest {

    @Test
    public void testNoArgsConstructor() {
        // When
        Suggestion suggestion = new Suggestion();

        // Then
        assertNotNull(suggestion);
        assertNull(suggestion.getSuggestionId());
        assertNull(suggestion.getUserId());
        assertNull(suggestion.getContent());
    }

    @Test
    public void testAllArgsConstructor() {
        // Given
        Long suggestionId = 1L;
        Long userId = 10L;
        String content = "Test content";

        // When
        Suggestion suggestion = new Suggestion(suggestionId, userId, content);

        // Then
        assertNotNull(suggestion);
        assertEquals(suggestionId, suggestion.getSuggestionId());
        assertEquals(userId, suggestion.getUserId());
        assertEquals(content, suggestion.getContent());
    }

    @Test
    public void testGettersAndSetters() {
        // Given
        Suggestion suggestion = new Suggestion();

        Long suggestionId = 1L;
        Long userId = 10L;
        String content = "Test content";

        // When
        suggestion.setSuggestionId(suggestionId);
        suggestion.setUserId(userId);
        suggestion.setContent(content);

        // Then
        assertEquals(suggestionId, suggestion.getSuggestionId());
        assertEquals(userId, suggestion.getUserId());
        assertEquals(content, suggestion.getContent());
    }
}
