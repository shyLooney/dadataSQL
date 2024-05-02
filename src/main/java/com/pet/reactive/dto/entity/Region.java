package com.pet.reactive.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("region")
public class Region {
    @Id
    private Long id;
    private UUID regionFiasId;
    private String regionKladrId;
    private String regionIsoCode;
    private String regionWithType;
    private String regionType;
    private String regionTypeFull;
    private String region;
    private Long countryId;
}
