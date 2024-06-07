package com.rent.rentcar.dto.rent;

import com.rent.rentcar.models.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RentMapper {
    RentMapper INSTANCE = Mappers.getMapper(RentMapper.class);

    RentDto toDto(Rent rent);
    Rent saveDtoToEntity(RentToSaveDto rentToSaveDto);
    Rent toEntity(RentDto rentDto);
}
