package com.rent.rentcar.repository;

import com.rent.rentcar.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class CarRepositoryTest extends AbstractIntegrationDBTest {

    @Autowired
    CarRepository carRepository;

    void initMockCar() {
        Car car = Car.builder()
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

        Car car2 = Car.builder()
                .brand("Honda")
                .model("Civic")
                .color("Negro")
                .year("2021")
                .licensePlate("P-123ABD")
                .price(200.00)
                .available(true)
                .description("Auto de lujo")
                .city(createCity())
                .build();
        carRepository.save(car2);
        carRepository.flush();
    }

    @BeforeEach
    void setUp() {
        carRepository.deleteAll();
    }

    @Test
    void givenCar_whenSaveCar_thenCarIsSaved() {
        Car car = Car.builder()
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

        Car savedCar = carRepository.save(car);

        assertThat(savedCar).isNotNull();
        assertThat(savedCar.getId()).isNotNull();
    }

    @Test
    void givenCar_whenFindById_thenReturnCar() {
        Car car = Car.builder()
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

        Car savedCar = carRepository.save(car);

        Optional<Car> foundCar = carRepository.findById(savedCar.getId());

        assertThat(foundCar).isPresent();
        assertThat(foundCar.get().getBrand()).isEqualTo("Toyota");
    }

    @Test
    void givenCar_whenUpdateCar_thenCarIsUpdated() {
        Car car = Car.builder()
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

        Car savedCar = carRepository.save(car);

        savedCar.setColor("Negro");
        Car updatedCar = carRepository.save(savedCar);

        assertThat(updatedCar.getColor()).isEqualTo("Negro");
    }

    @Test
    void givenCar_whenDeleteCar_thenCarIsDeleted() {
        Car car = Car.builder()
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

        Car savedCar = carRepository.save(car);

        carRepository.deleteById(savedCar.getId());

        Optional<Car> deletedCar = carRepository.findById(savedCar.getId());

        assertThat(deletedCar).isNotPresent();
    }

    @Test
    void findByCity_Name_ShouldReturnListOfCarsWithMatchingCityName() {
        initMockCar();

        List<Car> carsInSantaMarta = carRepository.findByCity_Name("Santa Marta");

        assertThat(carsInSantaMarta).hasSize(2);
    }

    @Test
    void findByAvailableTrue_ShouldReturnListOfAvailableCars() {

        initMockCar();

        List<Car> availableCars = carRepository.findByAvailableTrue();

        assertThat(availableCars).hasSize(2);
    }
}
