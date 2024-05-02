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
@Table("house")
public class House {
    @Id
    private Long id;
    private UUID houseFiasId;
    private String houseKladrId;
    private String houseType;
    private String houseTypeFull;
    private String house;
    private String blockType;
    private String blockTypeFull;
    private String block;
    private Long streetId;
    private Long settlementId;
}
