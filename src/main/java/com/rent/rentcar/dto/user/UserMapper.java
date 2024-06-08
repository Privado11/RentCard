package com.rent.rentcar.dto.user;

import com.rent.rentcar.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User saveDtoToEntity(UserToSaveDto userToSaveDto);
    User toEntity(UserDto userDto);
}
