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
@Table("city")
public class City {
    @Id
    private Long id;
    private UUID cityFiasId;
    private String cityKladrId;
    private String cityWithType;
    private String cityType;
    private String cityTypeFull;
    private String city;
    private Long regionId;
}
