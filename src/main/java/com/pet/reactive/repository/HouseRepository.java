package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.House;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface HouseRepository extends R2dbcRepository<House, Long> {
    Mono<House> findByHouseFiasId(UUID houseFiasId);
}
