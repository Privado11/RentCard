package com.rent.rentcar.dto.city;

import com.rent.rentcar.models.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CityMapperTest {

    private CityMapper cityMapper;

    @BeforeEach
    void setUp() {
        cityMapper = CityMapper.INSTANCE;
    }

    @Test
    void toDto() {
        City city = new City(1L, "CityName");

        CityDto cityDto = cityMapper.toDto(city);

        assertNotNull(cityDto);
        assertEquals(city.getId(), cityDto.id());
        assertEquals(city.getName(), cityDto.name());
    }

    @Test
    void saveDtoToEntity() {

        CityToSaveDto cityDto = new CityToSaveDto(1L, "CityName");

        City city = cityMapper.saveDtoToEntity(cityDto);


        assertNotNull(city);
        assertEquals(cityDto.id(), city.getId());
        assertEquals(cityDto.name(), city.getName());
    }

    @Test
    void toEntity() {

        CityDto cityDto = new CityDto(1L, "CityName");

        City city = cityMapper.toEntity(cityDto);

        assertNotNull(city);
        assertEquals(cityDto.id(), city.getId());
        assertEquals(cityDto.name(), city.getName());
    }
}
