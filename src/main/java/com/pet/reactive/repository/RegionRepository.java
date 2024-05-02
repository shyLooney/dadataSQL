package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.Region;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface RegionRepository extends R2dbcRepository<Region, Long> {
    Mono<Region> findByRegionTypeFull(String regionTypeFull);
}
