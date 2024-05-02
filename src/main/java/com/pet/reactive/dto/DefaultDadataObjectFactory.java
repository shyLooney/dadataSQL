package com.pet.reactive.dto;

import com.pet.reactive.dto.entity.*;
import com.pet.reactive.dto.entity.Country;
import com.pet.reactive.entity.DataDadata;
import com.pet.reactive.entity.Suggestion;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class DefaultDadataObjectFactory extends DadataObjectFactory {

    public DefaultDadataObjectFactory(Suggestion suggestion) {
        super(suggestion);
    }

    @Override
    public Country createCountry() {
        return new Country(
                null,
                dadata.getPostalCode() == null ? null : Integer.parseInt(dadata.getPostalCode()),
                dadata.getCountry(),
                dadata.getCountryIsoCode(),
                dadata.getFederalDistrict());
    }

    @Override
    public Region createRegion(Long countryId) {

        return new Region(
                null,
                UUID.fromString(dadata.getRegionFiasId()).toString().equals(dadata.getRegionFiasId()) ? UUID.fromString(dadata.getRegionFiasId()) : null,
                dadata.getRegionKladrId(),
                dadata.getRegionIsoCode(),
                dadata.getRegionWithType(),
                dadata.getRegionType(),
                dadata.getRegionTypeFull(),
                dadata.getRegion(),
                countryId
        );
    }

    @Override
    public Area createArea(Long regionId) {
        if (dadata.getAreaFiasId() == null)
            return null;
        return new Area(
                null,
                UUID.fromString(dadata.getAreaFiasId()).toString().equals(dadata.getAreaFiasId()) ? UUID.fromString(dadata.getAreaFiasId()) : null,
                dadata.getAreaKladrId(),
                dadata.getAreaWithType(),
                dadata.getAreaType(),
                dadata.getAreaTypeFull(),
                dadata.getArea(),
                regionId
        );
    }

    @Override
    public City createCity(Long regionId) {
        if (dadata.getCityFiasId() == null)
            return null;
        return new City(
                null,
                UUID.fromString(dadata.getCityFiasId()).toString().equals(dadata.getCityFiasId()) ? UUID.fromString(dadata.getCityFiasId()) : null,
                dadata.getCityKladrId(),
                dadata.getCityWithType(),
                dadata.getCityType(),
                dadata.getCityTypeFull(),
                dadata.getCity(),
                regionId
        );
    }

    @Override
    public CityDistrict createCityDistrict(Long cityId) {
        if (dadata.getCityDistrictFiasId() == null) {
            return null;
        }

        return new CityDistrict(
                null,
                dadata.getCityArea(),
                UUID.fromString(dadata.getCityDistrictFiasId()).toString().equals(dadata.getCityDistrictFiasId()) ? UUID.fromString(dadata.getCityDistrict()) : null,
                dadata.getCityDistrictKladrId(),
                dadata.getCityDistrictWithType(),
                dadata.getCityDistrictType(),
                dadata.getCityDistrictTypeFull(),
                dadata.getCityDistrict(),
                cityId
        );
    }

    @Override
    public Settlement createSettlement(Long areaId, Long cityDistrictId) {
        if (dadata.getSettlementFiasId() == null)
            return null;

        return new Settlement(
                null,
                UUID.fromString(dadata.getSettlementFiasId()).toString().equals(dadata.getSettlementFiasId()) ? UUID.fromString(dadata.getSettlementFiasId()) : null,
                dadata.getSettlementKladrId(),
                dadata.getSettlementWithType(),
                dadata.getSettlementType(),
                dadata.getSettlementTypeFull(),
                dadata.getSettlement(),
                areaId,
                cityDistrictId
        );
    }

    @Override
    public Street createStreet(Long settlementId, Long cityId) {
        if (dadata.getStreetFiasId() == null)
            return null;

        return new Street(
                null,
                UUID.fromString(dadata.getStreetFiasId()).toString().equals(dadata.getStreetFiasId()) ? UUID.fromString(dadata.getStreetFiasId()) : null,
                dadata.getStreetKladrId(),
                dadata.getStreetWithType(),
                dadata.getStreetType(),
                dadata.getStreetTypeFull(),
                dadata.getStreet(),
                settlementId,
                cityId
        );
    }

    @Override
    public House createHouse(Long streetId, Long settlementId) {
        if (dadata.getHouseFiasId() == null)
            return null;

        return new House(
                null,
                UUID.fromString(dadata.getHouseFiasId()).toString().equals(dadata.getHouseFiasId()) ? UUID.fromString(dadata.getHouseFiasId()) : null,
                dadata.getHouseKladrId(),
                dadata.getHouseType(),
                dadata.getHouseTypeFull(),
                dadata.getHouse(),
                dadata.getBlockType(),
                dadata.getBlockTypeFull(),
                dadata.getBlock(),
                streetId,
                settlementId
        );
    }

    @Override
    public Flat createFlat(Long houseId) {
        if (dadata.getFlatFiasId() == null)
            return null;

        return new Flat(
                null,
                UUID.fromString(dadata.getFlatFiasId()).toString().equals(dadata.getFlatFiasId()) ? UUID.fromString(dadata.getFlatFiasId()) : null,
                dadata.getFlatType(),
                dadata.getFlatTypeFull(),
                dadata.getFlat() == null ? null : Integer.parseInt(dadata.getFlat()),
                dadata.getFlatArea(),
                dadata.getSquareMeterPrice(),
                dadata.getFlatPrice() == null ? null : Double.parseDouble(dadata.getFlatPrice()),
                dadata.getPostalBox(),
                houseId
        );
    }

    @Override
    public CommonResult createCommonResult() {
        return new CommonResult(
                null,
                suggestion.getValue(),
                UUID.fromString(dadata.getFiasId()).toString().equals(dadata.getFiasId()) ? UUID.fromString(dadata.getFiasId()) : null,
                dadata.getFiasLevel() == null ? null : Integer.parseInt(dadata.getFiasLevel()),
                dadata.getFiasActualityState() == null ? null : Integer.parseInt(dadata.getFiasActualityState()),
                dadata.getKladrId(),
                dadata.getCapitalMarker() == null ? null : Integer.parseInt(dadata.getCapitalMarker()),
                dadata.getOkato(),
                dadata.getOktmo(),
                dadata.getTaxOffice(),
                dadata.getTaxOfficeLegal(),
                null
        );
    }
}
