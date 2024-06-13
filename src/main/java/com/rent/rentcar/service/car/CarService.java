package com.rent.rentcar.service.car;


import com.rent.rentcar.dto.car.CarDto;
import com.rent.rentcar.dto.car.CarToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface CarService {
    CarDto getCarById(Long id) throws NotFoundExceptionEntity;
    CarDto addCar(CarToSaveDto carToSaveDto);
    CarDto updateCar(Long id, CarToSaveDto carToSaveDto) throws NotFoundExceptionEntity;
    void deleteCar(Long id);
    List<CarDto> getAllCars();
    List<CarDto> getAllAvailableCars();
    List<CarDto> getAllCarsByCityName(String city);
    List<CarDto> findAvailableCarsInCity(Long cityId, LocalDateTime startDate, LocalDateTime endDate);

}
