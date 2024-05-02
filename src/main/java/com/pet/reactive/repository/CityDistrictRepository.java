package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.CityDistrict;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CityDistrictRepository extends R2dbcRepository<CityDistrict, Long> {
    Mono<CityDistrict> findByCityDistrictTypeFull(String cityDistrictTypeFull);
}
