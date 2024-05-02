package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.Street;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface StreetRepository extends R2dbcRepository<Street, Long> {
    Mono<Street> findByStreetFiasId(UUID streetFiasId);
}
