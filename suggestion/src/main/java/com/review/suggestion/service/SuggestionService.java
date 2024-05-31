package com.review.suggestion.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.review.suggestion.entity.Suggestion;
import com.review.suggestion.repository.SuggestionRepository;

@Service
public class SuggestionService {
	
	@Autowired
	private SuggestionRepository repository;
	
	public Suggestion submitSuggestion(Suggestion suggestion) {
		return Optional.ofNullable(suggestion)
			.map(repository::save)
			.orElseThrow(() -> new IllegalArgumentException("Suggestion cannot be null"));
	}
	
	public List<Suggestion> getAllSuggestions(){
		return Optional.ofNullable(repository.findAll())
        .filter(list -> !list.isEmpty())
        .orElseThrow(() -> new RuntimeException("Failed to retrieve suggestions."));
	}
}
