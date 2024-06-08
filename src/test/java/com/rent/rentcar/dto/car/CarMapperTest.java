package com.rent.rentcar.dto.car;

import com.rent.rentcar.dto.city.CityDto;
import com.rent.rentcar.models.Car;
import com.rent.rentcar.models.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CarMapperTest {

    private CarMapper carMapper;

    @BeforeEach
    void setUp() {
        carMapper = CarMapper.INSTANCE;
    }

    @Test
    void toDto() {

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
                .city(new City(1l,"CityName"))
                .build();

        CarDto carDto = carMapper.toDto(car);


        assertNotNull(carDto);
        assertEquals(car.getId(), carDto.id());
        assertEquals(car.getBrand(), carDto.brand());
        assertEquals(car.getModel(), carDto.model());
        assertEquals(car.getColor(), carDto.color());
        assertEquals(car.getYear(), carDto.year());
        assertEquals(car.getLicensePlate(), carDto.licensePlate());
        assertEquals(car.getPrice(), carDto.price());
        assertEquals(car.isAvailable(), carDto.available());
        assertEquals(car.getDescription(), carDto.description());
        assertEquals(car.getCity().getName(), carDto.city().name());
    }

    @Test
    void saveDtoToEntity() {

        CarToSaveDto carDto = new CarToSaveDto(1L, "Toyota", "Corolla", "Blanco", "2021",
                "P-123ABC", 200.00, true, "Auto de lujo", new City(1l,"CityName"));


        Car car = carMapper.saveDtoToEntity(carDto);


        assertNotNull(car);
        assertEquals(carDto.id(), car.getId());
        assertEquals(carDto.brand(), car.getBrand());
        assertEquals(carDto.model(), car.getModel());
        assertEquals(carDto.color(), car.getColor());
        assertEquals(carDto.year(), car.getYear());
        assertEquals(carDto.licensePlate(), car.getLicensePlate());
        assertEquals(carDto.price(), car.getPrice());
        assertEquals(carDto.available(), car.isAvailable());
        assertEquals(carDto.description(), car.getDescription());
    }

    @Test
    void toEntity() {

        CarDto carDto = new CarDto(1L, "Toyota", "Corolla", "Blanco", "2021",
                "P-123ABC", 200.00, true, "Auto de lujo", new CityDto(1l, "CityName"));


        Car car = carMapper.toEntity(carDto);

        assertNotNull(car);
        assertEquals(carDto.id(), car.getId());
        assertEquals(carDto.brand(), car.getBrand());
        assertEquals(carDto.model(), car.getModel());
        assertEquals(carDto.color(), car.getColor());
        assertEquals(carDto.year(), car.getYear());
        assertEquals(carDto.licensePlate(), car.getLicensePlate());
        assertEquals(carDto.price(), car.getPrice());
        assertEquals(carDto.available(), car.isAvailable());
        assertEquals(carDto.description(), car.getDescription());
        assertEquals(carDto.city().name(), car.getCity().getName());
    }
}
