package com.pet.reactive.dto;

import com.pet.reactive.dto.entity.*;
import com.pet.reactive.entity.DataDadata;
import com.pet.reactive.entity.Suggestion;


public abstract class DadataObjectFactory {
    protected final Suggestion suggestion;
    protected final DataDadata dadata;

    protected DadataObjectFactory(Suggestion suggestion) {
        this.suggestion = suggestion;
        this.dadata = suggestion.getData();
    }

    public abstract Country createCountry();
    public abstract Region createRegion(Long countryId);
    public abstract Area createArea(Long regionId);
    public abstract City createCity(Long regionId);
    public abstract CityDistrict createCityDistrict(Long cityId);
    public abstract Settlement createSettlement(Long areaId, Long cityDistrictId);
    public abstract Street createStreet(Long settlementId, Long cityId);
    public abstract House createHouse(Long streetId, Long settlementId);
    public abstract Flat createFlat(Long houseId);
    public abstract CommonResult createCommonResult();
}
