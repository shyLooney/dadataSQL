package com.pet.reactive.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    @Id
    private Long id;
    private String fullAddress;
    private UUID fiasId;
    private Integer fiasLevel;
    private Integer fiasActualityState;
    private String kladrId;
    private Integer capitalMarker;
    private String okato;
    private String oktmo;
    private String taxOffice;
    private String taxOfficeLegal;
    private Integer geoResultId;
}
