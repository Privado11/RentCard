package com.rent.rentcar.service.car;

import com.rent.rentcar.dto.car.CarDto;
import com.rent.rentcar.dto.car.CarMapper;
import com.rent.rentcar.dto.car.CarToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.Car;
import com.rent.rentcar.models.City;
import com.rent.rentcar.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    private Car car;
    private CarDto carDto;

    @BeforeEach
    void setUp() {
        Car car = Car.builder()
                .id(1L)
                .brand("Toyota")
                .model("Corolla")
                .color("Blanco")
                .year("2021")
                .licensePlate("P-123ABC")
                .price(200.00)
                .available(true)
                .description("Auto de lujo")
                .imageUrl(null)
                .transmissionType(null)
                .reservations(null)
                .build();
        carDto = new CarDto(1L, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", null);
    }

    @Test
    void testGetCarById() throws NotFoundExceptionEntity {
        Long carId = 1L;
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        when(carMapper.toDto(car)).thenReturn(carDto);

        CarDto result = carService.getCarById(carId);

        assertNotNull(result);
        assertEquals(car.getId(), result.id());
        assertEquals(car.getBrand(), result.brand());
    }

    @Test
    void testAddCar() {
        CarToSaveDto newCarDto = new CarToSaveDto(1l, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", City.builder().build());
        when(carMapper.saveDtoToEntity(any())).thenReturn(car);
        when(carRepository.save(car)).thenReturn(car);
        when(carMapper.toDto(car)).thenReturn(carDto);

        CarDto result = carService.addCar(newCarDto);

        assertNotNull(result);
        assertNotNull(result.id());
        assertEquals(car.getBrand(), result.brand());
    }

    @Test
    void testDeleteCar() {
        Long carId = 1L;
        when(carRepository.findById(carId)).thenReturn(Optional.of(car));
        carService.deleteCar(carId);

        verify(carRepository, times(1)).delete(car);
    }

    @Test
    void testGetAllCars() {
        List<Car> carList = List.of(
                new Car(1L, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", null,null,null,null),
                new Car(1L, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", null,null,null,null)
        );
        when(carRepository.findAll()).thenReturn(carList);

        List<CarDto> result = carService.getAllCars();

        assertNotNull(result);
        assertEquals(carList.size(), result.size());
    }

    @Test
    void testGetAllAvailableCars() {
        List<Car> availableCars = List.of(
                new Car(1L, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", null,null,null,null),
                new Car(1L, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", null,null,null,null)
        );
        when(carRepository.findByAvailableTrue()).thenReturn(availableCars);


        List<CarDto> result = carService.getAllAvailableCars();

        assertNotNull(result);
        assertEquals(availableCars.size(), result.size());
    }

//    @Test
//    void testGetAllCarsByCityName() {
//        String cityName = "City";
//        List<Car> carsInCity = List.of(
//                new Car(1L, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", null,null,null,null),
//                new Car(1L, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", null,null,null,null)
//        );
//        when(carRepository.findByCity_Name(cityName)).thenReturn(carsInCity);
//
//        List<CarDto> result = carService.getAllCarsByCityName(cityName);
//
//        assertNotNull(result);
//        assertEquals(carsInCity.size(), result.size());
//    }
}
