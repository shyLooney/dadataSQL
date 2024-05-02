package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.City;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CityRepository extends R2dbcRepository<City, Long> {
    Mono<City> findByCityTypeFull(String cityTypeFull);
}
