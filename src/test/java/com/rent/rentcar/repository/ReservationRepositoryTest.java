package com.rent.rentcar.repository;

import com.rent.rentcar.models.Car;
import com.rent.rentcar.models.Reservation;
import com.rent.rentcar.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryTest extends AbstractIntegrationDBTest{


    RentRepository rentRepository;
    UserRepository userRepository;
    CarRepository carRepository;

    @Autowired
    public ReservationRepositoryTest(RentRepository rentRepository, UserRepository userRepository, CarRepository carRepository) {
        this.rentRepository = rentRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }

    Car car;
    User user;

    void initMockCar() {
        car = Car.builder()
                .brand("Toyota")
                .model("Corolla")
                .color("Blanco")
                .year("2021")
                .licensePlate("P-123ABC")
                .price(200.00)
                .available(true)
                .description("Auto de lujo")
                .city(createCity())
                .build();
        carRepository.save(car);
        carRepository.flush();

        user = User.builder()
                .name("John")
                .lastName("Doe")
                .idCard("1234567")
                .address("123 Main St")
                .phone("555-1234")
                .build();
        userRepository.save(user);
        userRepository.flush();
    }

    @BeforeEach
    void setUp() {
        rentRepository.deleteAll();
        userRepository.deleteAll();
        carRepository.deleteAll();
    }

    @Test
    void givenRent_whenSaveRent_thenRentIsSaved() {
        initMockCar();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plus(Duration.ofDays(5));
        double totalPrice = 1000.00;

        Reservation reservation = Reservation.builder()
                .car(car)
                .user(user)
                .startDate(startDate)
                .endDate(endDate)
                .totalPrice(totalPrice)
                .build();

        Reservation savedReservation = rentRepository.save(reservation);
        assertNotNull(savedReservation.getId());
    }

    @Test
    void givenRent_whenFindAllRents_thenRentsAreFound() {
        initMockCar();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plus(Duration.ofDays(5));
        double totalPrice = 1000.00;

        Reservation reservation = Reservation.builder()
                .car(car)
                .user(user)
                .startDate(startDate)
                .endDate(endDate)
                .totalPrice(totalPrice)
                .build();

        rentRepository.save(reservation);

        Iterable<Reservation> rents = rentRepository.findAll();
        assertTrue(rents.iterator().hasNext());
    }

    @Test
    void givenRent_whenUpdateRent_thenRentIsUpdated() {
        initMockCar();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plus(Duration.ofDays(5));
        double totalPrice = 1000.00;

        Reservation reservation = Reservation.builder()
                .car(car)
                .user(user)
                .startDate(startDate)
                .endDate(endDate)
                .totalPrice(totalPrice)
                .build();

        Reservation savedReservation = rentRepository.save(reservation);
        LocalDateTime newEndDate = endDate.plus(Duration.ofDays(3));
        savedReservation.setEndDate(newEndDate);

        Reservation updatedReservation = rentRepository.save(savedReservation);

        assertEquals(newEndDate, updatedReservation.getEndDate());
    }

    @Test
    void givenRent_whenDeleteRent_thenRentIsDeleted() {
        initMockCar();
        LocalDateTime startDate = LocalDateTime.now();
        LocalDateTime endDate = startDate.plus(Duration.ofDays(5));
        double totalPrice = 1000.00;

        Reservation reservation = Reservation.builder()
                .car(car)
                .user(user)
                .startDate(startDate)
                .endDate(endDate)
                .totalPrice(totalPrice)
                .build();

        Reservation savedReservation = rentRepository.save(reservation);
        rentRepository.deleteById(savedReservation.getId());

        assertFalse(rentRepository.existsById(savedReservation.getId()));
    }
}