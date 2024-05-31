package com.review.suggestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.review.suggestion.entity.Suggestion;

@Repository
public interface SuggestionRepository extends JpaRepository<Suggestion,Long> {

}
