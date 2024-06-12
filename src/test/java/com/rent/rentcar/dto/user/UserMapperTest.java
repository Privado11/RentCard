package com.rent.rentcar.dto.user;

import com.rent.rentcar.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperTest {

    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        userMapper = UserMapper.INSTANCE;
    }

    @Test
    void toDto() {
        // Given
        User user = new User(1L, "John", "Doe", "1234567", "privado@privado.com","123 Main St", "555-1234", "Privado", null);

        // When
        UserDto userDto = userMapper.toDto(user);

        // Then
        assertNotNull(userDto);
        assertEquals(user.getId(), userDto.id());
        assertEquals(user.getName(), userDto.name());
        assertEquals(user.getLastName(), userDto.lastName());
        assertEquals(user.getIdCard(), userDto.idCard());
        assertEquals(user.getAddress(), userDto.address());
        assertEquals(user.getPhone(), userDto.phone());
    }

    @Test
    void saveDtoToEntity() {
        // Given
        UserToSaveDto userDto = new UserToSaveDto(1L, "John", "Doe", "1234567", "123 Main St", "555-1234");

        // When
        User user = userMapper.saveDtoToEntity(userDto);

        // Then
        assertNotNull(user);
        assertEquals(userDto.id(), user.getId());
        assertEquals(userDto.name(), user.getName());
        assertEquals(userDto.lastName(), user.getLastName());
        assertEquals(userDto.idCard(), user.getIdCard());
        assertEquals(userDto.address(), user.getAddress());
        assertEquals(userDto.phone(), user.getPhone());
    }

    @Test
    void toEntity() {
        // Given
        UserDto userDto = new UserDto(1L, "John", "Doe", "1234567", "123 Main St", "555-1234");

        // When
        User user = userMapper.toEntity(userDto);

        // Then
        assertNotNull(user);
        assertEquals(userDto.id(), user.getId());
        assertEquals(userDto.name(), user.getName());
        assertEquals(userDto.lastName(), user.getLastName());
        assertEquals(userDto.idCard(), user.getIdCard());
        assertEquals(userDto.address(), user.getAddress());
        assertEquals(userDto.phone(), user.getPhone());
    }
}
