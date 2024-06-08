package com.rent.rentcar.repository;

import com.rent.rentcar.models.City;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class CreateCityTest {

    @Autowired
    CityRepository cityRepository;

    City createCity() {
        City city = City.builder()
                .name("Santa Marta")
                .build();
        cityRepository.save(city);

        return city;
    }


}

