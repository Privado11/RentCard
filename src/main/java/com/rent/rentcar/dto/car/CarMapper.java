package com.rent.rentcar.dto.car;

import com.rent.rentcar.models.Car;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    CarDto toDto(Car car);
    Car saveDtoToEntity(CarToSaveDto carToSaveDto);
    Car toEntity(CarDto carDto);
}
