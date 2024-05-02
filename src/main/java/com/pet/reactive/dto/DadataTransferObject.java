package com.pet.reactive.dto;

import com.pet.reactive.dto.entity.*;
import com.pet.reactive.entity.DataDadata;
import com.pet.reactive.entity.Suggestion;
import com.pet.reactive.repository.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Data
@RequiredArgsConstructor
@Component
@Slf4j
public class DadataTransferObject {
    //    private DataDadata dadata;
    private DadataObjectFactory factory;

    private final CountryRepository countryRepository;
    private final RegionRepository regionRepository;
    private final AreaRepository areaRepository;
    private final CityRepository cityRepository;
    private final CityDistrictRepository cityDistrictRepository;
    private final SettlementRepository settlementRepository;
    private final StreetRepository streetRepository;
    private final HouseRepository houseRepository;
    private final FlatRepository flatRepository;
    private final CommonResultRepository commonResultRepository;

    public void save(Suggestion suggestion) {
        factory = new DefaultDadataObjectFactory(suggestion);

        Country country = factory.createCountry();
        log.info(suggestion.toString());
        log.info(country.toString());

        countryRepository
                .findCountryByPostalCode(country.getPostalCode())
                .switchIfEmpty(countryRepository.save(country))
                .flatMap(this::insertRegion)
                .flatMap(region -> {
                    Area areaObj = factory.createArea(region.getId());
                    City cityObj = factory.createCity(region.getId());

                    if (areaObj == null && cityObj == null) {
                        return Mono.just(region);
                    } else if (areaObj == null) {
                        log.info(cityObj.toString());


                        return cityRepository.findByCityTypeFull(cityObj.getCityTypeFull())
                                .switchIfEmpty(cityRepository.save(cityObj))
                                .flatMap(city -> {
                                    CityDistrict cityDistrictObj = factory.createCityDistrict(city.getId());
                                    Street streetObj = factory.createStreet(null, city.getId());

                                    streetRepository.findOne(Example.of(streetObj)).subscribe(System.out::println);

                                    if (cityDistrictObj != null) {
                                        log.info(streetObj.toString());

                                        cityDistrictRepository.exists(Example.of(cityDistrictObj))
                                                .subscribe(cityDistrictInfo -> {
                                                    if (cityDistrictInfo)
                                                        cityDistrictRepository.save(cityDistrictObj).subscribe();
                                                });
                                    }

                                    if (streetObj == null) {
                                        return Mono.just(city);
                                    } else {
                                        log.info(streetObj.toString());

                                        return streetRepository.findByStreetFiasId(streetObj.getStreetFiasId())
                                                .switchIfEmpty(streetRepository.save(streetObj))
                                                .flatMap(street -> {
                                                    House houseObj = factory.createHouse(street.getId(), null);
                                                    if (houseObj == null) {
                                                        return Mono.just(street);
                                                    } else
                                                        log.info(houseObj.toString());

                                                    return houseRepository.findByHouseFiasId(houseObj.getHouseFiasId())
                                                            .switchIfEmpty(houseRepository.save(houseObj))
                                                            .flatMap(house -> {
                                                                Flat flatObj = factory.createFlat(house.getId());

                                                                if (flatObj == null) {
                                                                    return Mono.just(house);
                                                                } else {
                                                                    log.info(flatObj.toString());

                                                                    return flatRepository.findByFlatFiasId(flatObj.getFlatFiasId())
                                                                            .switchIfEmpty(flatRepository.save(flatObj));
                                                                }
                                                            });
                                                });
                                    }
                                });
                    } else
                        log.info(areaObj.toString());

                    return areaRepository.findAreaByAreaTypeFull(areaObj.getAreaTypeFull())
                            .switchIfEmpty(areaRepository.save(areaObj))
                            .flatMap(area -> {
                                Settlement settlementObj = factory.createSettlement(area.getId(), null);

                                if (settlementObj == null) {
                                    return Mono.just(area);
                                } else {
                                    log.info(settlementObj.toString());

                                    return settlementRepository.findBySettlementTypeFull(settlementObj.getSettlementTypeFull())
                                            .switchIfEmpty(settlementRepository.save(settlementObj))
                                            .flatMap(settlement -> {
                                                Street streetObj = factory.createStreet(settlement.getId(), null);

                                                if (streetObj == null) {
                                                    return Mono.just(settlement);
                                                } else
                                                    log.info(streetObj.toString());

                                                return streetRepository.findByStreetFiasId(streetObj.getStreetFiasId())
                                                        .switchIfEmpty(streetRepository.save(streetObj))
                                                        .flatMap(street -> {
                                                            House houseObj = factory.createHouse(street.getId(), null);

                                                            if (houseObj == null) {
                                                                return Mono.just(street);
                                                            } else {
                                                                log.info(houseObj.toString());

                                                                return houseRepository.findByHouseFiasId(houseObj.getHouseFiasId())
                                                                        .switchIfEmpty(houseRepository.save(houseObj))
                                                                        .flatMap(house -> {
                                                                            Flat flatObj = factory.createFlat(house.getId());

                                                                            if (flatObj == null) {
                                                                                return Mono.just(house);
                                                                            } else {
                                                                                log.info(flatObj.toString());

                                                                                return flatRepository.findByFlatFiasId(flatObj.getFlatFiasId())
                                                                                        .switchIfEmpty(flatRepository.save(flatObj));
                                                                            }
                                                                        });
                                                            }
                                                        });
                                            });
                                }
                            });
                })
                .subscribe();
    }

    public void checkAndUpdate(Suggestion suggestion) {
        DataDadata dadata = suggestion.getData();
        factory = new DefaultDadataObjectFactory(suggestion);


        commonResultRepository.findByFiasId(UUID.fromString(dadata.getFiasId()))
                .flatMap(commonResult -> {
                    if (commonResult == null) {
                        save(suggestion);
                    } else if (!commonResult.getFullAddress().equals(suggestion.getValue())) {
                        Mono<House> house = houseRepository.findByHouseFiasId(commonResult.getFiasId());
                        Mono<Street> street = house.flatMap(houseTemp -> streetRepository.findById(houseTemp.getStreetId()));
                        Mono<Settlement> settlement = street.flatMap(streetTemp -> settlementRepository.findById(streetTemp.getSettlementId()));
                        Mono<City> city = street.flatMap(streetTemp -> cityRepository.findById(streetTemp.getCityId()));
                        Mono<Region> region;

                        settlement.flatMap(settlementTemp -> {
//                            if (settlementTemp != null)
                            return city.flatMap(cityTemp -> regionRepository.findById(cityTemp.getRegionId()));
                        });

                    }
                    return Mono.just(commonResult);
                })
                .subscribe();
    }


    private Mono<Region> insertRegion(Country country) {
        Region regionObj = factory.createRegion(country.getId());

        log.info(regionObj.toString());

        return regionRepository.findOne(Example.of(regionObj))
                .switchIfEmpty(regionRepository.save(regionObj));
    }
}
