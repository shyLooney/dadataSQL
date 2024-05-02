package com.pet.reactive.repository;

import com.pet.reactive.dto.entity.Country;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface CountryRepository extends R2dbcRepository<Country, Long> {
    Mono<Country> findCountryByPostalCode(Integer postalCode);
}
