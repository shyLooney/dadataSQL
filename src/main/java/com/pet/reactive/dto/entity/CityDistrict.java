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
@Table("city_district")
public class CityDistrict {
    @Id
    private Long id;
    private String cityArea;
    private UUID cityDistrictFiasId;
    private String cityDistrictKladrId;
    private String cityDistrictWithType;
    private String cityDistrictType;
    private String cityDistrictTypeFull;
    private String cityDistrict;
    private Long cityId;
}
