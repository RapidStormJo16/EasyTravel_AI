package com.review.suggestion;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.review.suggestion.entity.Suggestion;
import com.review.suggestion.repository.SuggestionRepository;
import com.review.suggestion.service.SuggestionService;

public class SuggestionServiceTest {

    @Mock
    private SuggestionRepository suggestionRepository;

    @InjectMocks
    private SuggestionService suggestionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSubmitSuggestion_Success() {
        // Given
        Suggestion suggestion = new Suggestion();
        suggestion.setContent("Provide feedback");

        // Mock repository behavior
        when(suggestionRepository.save(any(Suggestion.class))).thenReturn(suggestion);

        // When
        Suggestion submittedSuggestion = suggestionService.submitSuggestion(suggestion);

        // Then
        assertNotNull(submittedSuggestion);
        assertEquals("Provide feedback", submittedSuggestion.getContent());
    }

    @Test
    public void testSubmitSuggestion_NullSuggestion() {
        // When-Then
        assertThrows(IllegalArgumentException.class, () -> {
            suggestionService.submitSuggestion(null);
        });
    }

    @Test
    public void testGetAllSuggestions_Success() {
        // Given
        List<Suggestion> suggestions = new ArrayList<>();
        suggestions.add(new Suggestion(1L,1L, "Provide feedback"));
        suggestions.add(new Suggestion(2L,2L, "Review suggestion"));

        // Mock repository behavior
        when(suggestionRepository.findAll()).thenReturn(suggestions);

        // When
        List<Suggestion> retrievedSuggestions = suggestionService.getAllSuggestions();

        // Then
        assertNotNull(retrievedSuggestions);
        assertEquals(2, retrievedSuggestions.size());
    }

    @Test
    public void testGetAllSuggestions_NoSuggestions() {
        // Given
        List<Suggestion> emptyList = new ArrayList<>();

        // Mock repository behavior
        when(suggestionRepository.findAll()).thenReturn(emptyList);

        // When-Then
        assertThrows(RuntimeException.class, () -> {
            suggestionService.getAllSuggestions();
        });
    }
}
