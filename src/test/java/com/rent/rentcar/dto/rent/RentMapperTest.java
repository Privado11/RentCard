package com.rent.rentcar.dto.rent;

import com.rent.rentcar.dto.car.CarDto;
import com.rent.rentcar.dto.user.UserDto;
import com.rent.rentcar.models.Car;
import com.rent.rentcar.models.User;
import com.rent.rentcar.models.Rent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class RentMapperTest {

    private RentMapper rentMapper;

    @BeforeEach
    void setUp() {
        rentMapper = RentMapper.INSTANCE;
    }

    @Test
    void toDto() {
        // Given
        User user = new User(1L, "John", "Doe", "1234567", "123 Main St", "555-1234");
        Car car = new Car(1L, "Toyota", "Corolla", "Blanco", "2021", "P-123ABC", 200.00, true, "Auto de lujo", null);
        Rent rent = new Rent(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 300.00, user, car);

        // When
        RentDto rentDto = rentMapper.toDto(rent);

        // Then
        assertNotNull(rentDto);
        assertEquals(rent.getId(), rentDto.id());
        assertEquals(rent.getUser().getId(), rentDto.user().id());

    }

    @Test
    void saveDtoToEntity() {
        // Given
        User userDto = new User(1L, "John", "Doe", "1234567", "123 Main St", "555-1234");
        Car carDto = new Car(1L, "Toyota", "Corolla", "Blanco", "2021", "P-123ABC", 200.00, true, "Auto de lujo", null);
        RentToSaveDto rentDto = new RentToSaveDto(1L, userDto, carDto, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 300.00);

        // When
        Rent rent = rentMapper.saveDtoToEntity(rentDto);

        // Then
        assertNotNull(rent);
        assertEquals(rentDto.id(), rent.getId());
    }

    @Test
    void toEntity() {
        // Given
        UserDto userDto = new UserDto(1L, "John", "Doe", "1234567", "123 Main St", "555-1234");
        CarDto carDto = new CarDto(1L, "Toyota", "Corolla", "Blanco", "2021", "P-123ABC", 200.00, true, "Auto de lujo", null);
        RentDto rentDto = new RentDto(1L, userDto, carDto, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 300.00);

        // When
        Rent rent = rentMapper.toEntity(rentDto);

        // Then
        assertNotNull(rent);
        assertEquals(rentDto.id(), rent.getId());
        assertEquals(rentDto.user().id(), rent.getUser().getId());
    }
}
