package com.pet.reactive.repository;

import com.pet.reactive.entity.Suggestion;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface SuggestionRepository extends R2dbcRepository<Suggestion, Long> {
}
