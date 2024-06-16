package com.rent.rentcar.service.user;

import com.rent.rentcar.dto.user.UserDto;
import com.rent.rentcar.dto.user.UserToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;

import java.util.List;

public interface UserService {
    UserDto getUserById(Long id) throws NotFoundExceptionEntity;
    UserDto addUser(UserToSaveDto userToSaveDto);
    UserDto updateUser(Long id, UserToSaveDto userToSaveDto) throws NotFoundExceptionEntity;
    void deleteUser(Long id);
    List<UserDto> getAllUsers();
    UserDto getUserByEmail(String email) throws NotFoundExceptionEntity;
}
