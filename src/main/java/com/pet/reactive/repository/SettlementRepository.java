package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.Settlement;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface SettlementRepository extends R2dbcRepository<Settlement, Long> {
    Mono<Settlement> findBySettlementTypeFull(String settlementTypeFull);
}
