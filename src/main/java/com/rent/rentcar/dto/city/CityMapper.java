package com.rent.rentcar.dto.city;

import com.rent.rentcar.models.City;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

        CityDto toDto(City city);
        City saveDtoToEntity(CityToSaveDto cityToSaveDto);
        City toEntity(CityDto cityDto);
}
