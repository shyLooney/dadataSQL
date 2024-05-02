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
@Table("street")
public class Street {
    @Id
    private Long id;
    private UUID streetFiasId;
    private String streetKladrId;
    private String streetWithType;
    private String streetType;
    private String streetTypeFull;
    private String street;
    private Long settlementId;
    private Long cityId;
}
