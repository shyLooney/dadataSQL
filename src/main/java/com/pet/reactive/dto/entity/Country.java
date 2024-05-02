package com.pet.reactive.dto.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("country")
public class Country {
    @Id
    private Long id;
//    @Column("postal_code")
    private Integer postalCode;
//    @Column("country")
    private String country;
//    @Column("country_iso_code")
    private String countryIsoCode;
//    @Column("federal_district")
    private String federalDistrict;
}
