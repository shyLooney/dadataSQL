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
@Table("settlement")
public class Settlement {
    @Id
    private Long id;
    private UUID settlementFiasId;
    private String settlementKladrId;
    private String settlementWithType;
    private String settlementType;
    private String settlementTypeFull;
    private String settlement;
    private Long areaId;
    private Long cityDistrictId;
}
