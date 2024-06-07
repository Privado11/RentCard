package com.rent.rentcar.dto.user;

import com.rent.rentcar.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface USerMapper {
    USerMapper INSTANCE = Mappers.getMapper(USerMapper.class);

    UserDto toDto(User user);
    User saveDtoToEntity(UserToSaveDto userToSaveDto);
    User toEntity(UserDto userDto);
}
