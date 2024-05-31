package com.ai.generation;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ai.client.AiClient;
import org.springframework.ai.client.Generation;
import org.springframework.ai.prompt.PromptTemplate;

import com.ai.generation.exception.GenerationException;
import com.ai.generation.service.GenerationService;

public class GenerationServiceTest {

    @Mock
    private AiClient aiClient;

    @InjectMocks
    private GenerationService generationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetItinerary_Success() {
        // Given
//        String destination = "Kochi";
//        int noOfDays = 4;
//        int budget = 10000;
//        String companions = "family";
//        String activities = "sightseeing";
//        String cuisine = "local";
//
//        PromptTemplate promptTemplate = new PromptTemplate("");
//        promptTemplate.add("destination", destination);
//        promptTemplate.add("noOfDays", noOfDays);
//        promptTemplate.add("budget", budget);
//        promptTemplate.add("companions", companions);
//        promptTemplate.add("activities", activities);
//        promptTemplate.add("cuisine", cuisine);
//
////        generation.setText("Generated itinerary HTML");
//        
//        Generation generation = mock(Generation.class);
//        when(generation.getText()).thenReturn("Generated itinerary HTML");
//
//        when(aiClient.generate((String)any())).thenReturn("Generated itinerary HTML");
//
//        // When
//        String itinerary = generationService.getItinerary(destination, noOfDays, budget, companions, activities, cuisine);
//
//        // Then
//        assertNotNull(itinerary);
//        assertTrue(itinerary.contains("Generated itinerary HTML"));
    }

    @Test
    public void testGetItinerary_Exception() {
        // Given
        String destination = "Kochi";
        int noOfDays = 4;
        int budget = 10000;
        String companions = "family";
        String activities = "sightseeing";
        String cuisine = "local";

        when(aiClient.generate((String)any())).thenThrow(new RuntimeException("AI Client error"));

        // When - Then
        assertThrows(GenerationException.class, () -> {
            generationService.getItinerary(destination, noOfDays, budget, companions, activities, cuisine);
        });
    }
}
