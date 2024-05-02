package com.pet.reactive.repository;

import com.pet.reactive.entity.SuggestionList;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SuggestionListRepository extends R2dbcRepository<SuggestionList, Id> {

}
