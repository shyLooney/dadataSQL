package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.Flat;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FlatRepository extends R2dbcRepository<Flat, Long> {
    Mono<Flat> findByFlatFiasId(UUID flatFiasId);
}
