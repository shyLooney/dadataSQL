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
@Table("flat")
public class Flat {
    @Id
    private Long id;
    private UUID flatFiasId;
    private String flatType;
    private String flatTypeFull;
    private Integer flat;
    private Double flatArea;
    private Double squareMeterPrice;
    private Double flatPrice;
    private String postalBox;
    private Long houseId;
}
