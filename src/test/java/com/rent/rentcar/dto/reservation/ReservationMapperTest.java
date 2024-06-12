package com.rent.rentcar.dto.reservation;

import com.rent.rentcar.dto.car.CarDto;
import com.rent.rentcar.dto.user.UserDto;
import com.rent.rentcar.models.Car;
import com.rent.rentcar.models.Reservation;
import com.rent.rentcar.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationMapperTest {

    private ReservationMapper reservationMapper;

    @BeforeEach
    void setUp() {
        reservationMapper = ReservationMapper.INSTANCE;
    }

    @Test
    void toDto() {
        // Given
        User user = new User(1L, "John", "Doe", "1234567", "privado@privado.com","123 Main St", "555-1234", "Privado", null);
        Car car = new Car(1L, "Toyota", "Corolla", "Blanco", "2021", "P-123ABC", 200.00, true, "Auto de lujo", null, null, null, null);
        Reservation reservation = new Reservation(1L, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 300.00, user, car, null);

        // When
        ReservationDto reservationDto = reservationMapper.toDto(reservation);

        // Then
        assertNotNull(reservationDto);
        assertEquals(reservation.getId(), reservationDto.id());
        assertEquals(reservation.getUser().getId(), reservationDto.user().id());

    }

    @Test
    void saveDtoToEntity() {
        // Given
        User userDto = new User(1L, "John", "Doe", "1234567", "privado@privado.com","123 Main St", "555-1234", "Privado", null);
        Car carDto = new Car(1L, "Toyota", "Corolla", "Blanco", "2021", "P-123ABC", 200.00, true, "Auto de lujo", null, null, null, null);
        ReservationToSaveDto rentDto = new ReservationToSaveDto(1L, userDto, carDto, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 300.00);

        // When
        Reservation reservation = reservationMapper.saveDtoToEntity(rentDto);

        // Then
        assertNotNull(reservation);
        assertEquals(rentDto.id(), reservation.getId());
    }

    @Test
    void toEntity() {
        // Given
        UserDto userDto = new UserDto(1L, "John", "Doe", "1234567", "123 Main St", "555-1234");
        CarDto carDto = new CarDto(1L, "Toyota", "Corolla", "Blanco", "2021", "P-123ABC", 200.00, true, "Auto de lujo", null);
        ReservationDto reservationDto = new ReservationDto(1L, userDto, carDto, LocalDateTime.now(), LocalDateTime.now().plusDays(3), 300.00);

        // When
        Reservation reservation = reservationMapper.toEntity(reservationDto);

        // Then
        assertNotNull(reservation);
        assertEquals(reservationDto.id(), reservation.getId());
        assertEquals(reservationDto.user().id(), reservation.getUser().getId());
    }
}
