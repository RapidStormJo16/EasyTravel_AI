package com.review.suggestion;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.review.suggestion.controller.SuggestionController;
import com.review.suggestion.entity.Suggestion;
import com.review.suggestion.service.SuggestionService;

public class SuggestionControllerTest {

    @Mock
    private SuggestionService suggestionService;

    @InjectMocks
    private SuggestionController suggestionController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSubmitSuggestion_Success() {
        // Given
        Suggestion suggestion = new Suggestion();
        suggestion.setContent("Provide feedback");

        // Mock service behavior
        when(suggestionService.submitSuggestion(any(Suggestion.class))).thenReturn(suggestion);

        // When
        ResponseEntity<?> response = suggestionController.submitSuggestion(suggestion);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(suggestion, response.getBody());
    }

    @Test
    public void testSubmitSuggestion_NullSuggestion() {
        // When
        ResponseEntity<?> response = suggestionController.submitSuggestion(null);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Cannot be empty", response.getBody());
    }

    @Test
    public void testSubmitSuggestion_Exception() {
        // Given
        Suggestion suggestion = new Suggestion();
        suggestion.setContent("Provide feedback");

        // Mock service behavior to throw an exception
        when(suggestionService.submitSuggestion(any(Suggestion.class))).thenThrow(new IllegalArgumentException("Invalid suggestion"));

        // When
        ResponseEntity<?> response = suggestionController.submitSuggestion(suggestion);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid suggestion", response.getBody());
    }
    
    @Test
    public void testSubmitSuggestion_AnyException() {
        // Given
        Suggestion suggestion = new Suggestion();
        suggestion.setContent("Provide feedback");

        // Mock service behavior to throw an exception
        when(suggestionService.submitSuggestion(any(Suggestion.class))).thenThrow(new RuntimeException("Invalid suggestion"));

        // When
        ResponseEntity<?> response = suggestionController.submitSuggestion(suggestion);

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("an error occurred: Invalid suggestion", response.getBody());
    }

    @Test
    public void testGetAllSuggestions_Success() {
        // Given
        List<Suggestion> suggestions = new ArrayList<>();
        suggestions.add(new Suggestion(1L,1L, "Review suggestion"));
        suggestions.add(new Suggestion(2L,2L, "Provide feedback"));

        // Mock service behavior
        when(suggestionService.getAllSuggestions()).thenReturn(suggestions);

        // When
        ResponseEntity<?> response = suggestionController.getAllSuggestions();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(suggestions, response.getBody());
    }

    @Test
    public void testGetAllSuggestions_Exception() {
        // Mock service behavior to throw an exception
        when(suggestionService.getAllSuggestions()).thenThrow(new RuntimeException("Failed to retrieve suggestions"));

        // When
        ResponseEntity<?> response = suggestionController.getAllSuggestions();

        // Then
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to retrieve suggestions", response.getBody());
    }
}
