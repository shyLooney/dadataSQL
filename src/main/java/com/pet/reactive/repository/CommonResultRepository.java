package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.CommonResult;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface CommonResultRepository extends R2dbcRepository<CommonResult, Long> {
    Mono<CommonResult> findByFiasId(UUID fiasId);
}
