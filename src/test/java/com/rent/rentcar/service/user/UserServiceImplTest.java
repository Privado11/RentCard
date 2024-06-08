package com.rent.rentcar.service.user;

import com.rent.rentcar.dto.user.UserDto;
import com.rent.rentcar.dto.user.UserMapper;
import com.rent.rentcar.dto.user.UserToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.User;
import com.rent.rentcar.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;



    @Test
    void testGetUserById() throws NotFoundExceptionEntity {
        Long userId = 1L;
        User user = new User(userId, "John", "Doe", "1234567", "123 Main St", "555-1234");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserDto userDto = new UserDto(userId, "John", "Doe", "1234567", "123 Main St", "555-1234");
        when(userMapper.toDto(user)).thenReturn(userDto);

        UserDto result = userService.getUserById(userId);

        assertNotNull(result);
        assertEquals(userId, result.id());
        assertEquals(user.getName(), result.name());
    }

    @Test
    void testAddUser() {
        UserDto newUserDto = new UserDto(1l, "John", "Doe", "1234567", "123 Main St", "555-1234");
        UserToSaveDto newUserDto1 = new UserToSaveDto(null, "John", "Doe", "1234567", "123 Main St", "555-1234");
        User newUser = new User(null, "John", "Doe", "1234567", "123 Main St", "555-1234");
        when(userRepository.save(newUser)).thenReturn(newUser);
        when(userMapper.saveDtoToEntity(newUserDto1)).thenReturn(newUser);
        when(userMapper.toDto(newUser)).thenReturn(newUserDto);




        UserDto result = userService.addUser(newUserDto1);

        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals(newUser.getName(), result.name());

    }

    @Test
    void testUpdateUser() throws NotFoundExceptionEntity {
        Long userId = 1L;
        UserDto updatedUserDto = new UserDto(userId, "Updated", "User", "1234567", "123 Main St", "555-1234");
        UserToSaveDto newUserDto1 = new UserToSaveDto(null, "John", "Doe", "1234567", "123 Main St", "555-1234");

        User updatedUser = new User(userId, "Updated", "User", "1234567", "123 Main St", "555-1234");
        when(userRepository.findById(userId)).thenReturn(Optional.of(updatedUser));
        when(userRepository.save(updatedUser)).thenReturn(updatedUser);
        when(userMapper.toDto(updatedUser)).thenReturn(updatedUserDto);



        UserDto result = userService.updateUser(userId, newUserDto1);

        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals("Updated", result.name());
    }

    @Test
    void testDeleteUser() {
        Long userId = 1L;
        User updatedUser = new User(userId, "Updated", "User", "1234567", "123 Main St", "555-1234");
        when(userRepository.findById(userId)).thenReturn(Optional.of(updatedUser));
        userService.deleteUser(userId);

        verify(userRepository, times(1)).delete(any());
    }

}
