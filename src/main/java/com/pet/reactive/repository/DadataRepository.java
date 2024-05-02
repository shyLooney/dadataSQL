package com.pet.reactive.repository;

import com.pet.reactive.entity.DataDadata;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface DadataRepository extends R2dbcRepository<DataDadata, Long> {
}
