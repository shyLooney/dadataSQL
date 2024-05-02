package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.Area;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface AreaRepository extends R2dbcRepository<Area, Long> {
    Mono<Area> findAreaByAreaTypeFull(String areaTypeFull);
}
