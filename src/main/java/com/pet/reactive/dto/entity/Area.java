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
@Table("area")
public class Area {
    @Id
    private Long id;
    private UUID areaFiasId;
    private String areaKladrId;
    private String areaWithType;
    private String areaType;
    private String areaTypeFull;
    private String area;
    private Long regionId;
}
