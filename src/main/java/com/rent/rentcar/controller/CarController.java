package com.rent.rentcar.controller;

import com.rent.rentcar.dto.car.CarDto;
import com.rent.rentcar.dto.car.CarToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.service.car.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = carService.getAllCars();
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id) {
        try {
            CarDto carDto = carService.getCarById(id);
            return ResponseEntity.ok(carDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CarDto> saveCar(@RequestBody CarToSaveDto carToSaveDto) {
        CarDto savedCar = carService.addCar(carToSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDto> updateCar(@PathVariable Long id, @RequestBody CarToSaveDto carToSaveDto) {
        try {
            CarDto updatedCar = carService.updateCar(id, carToSaveDto);
            return ResponseEntity.ok(updatedCar);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        carService.deleteCar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/available")
    public ResponseEntity<List<CarDto>> getAllAvailableCars() {
        List<CarDto> availableCars = carService.getAllAvailableCars();
        return ResponseEntity.ok(availableCars);
    }

    @GetMapping("/sales-branches/city")
    public ResponseEntity<List<CarDto>> getAllCarsByCityName(@RequestParam("cityName") String cityName) {
        List<CarDto> carsByCity = carService.getAllCarsByCityName(cityName);
        return ResponseEntity.ok(carsByCity);
    }
    @GetMapping("/availables")
    public ResponseEntity<List<CarDto>> findAvailableCarsInCity(
            @RequestParam("cityId") Long cityId,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<CarDto> availableCars = carService.findAvailableCarsInCity(cityId, startDate, endDate);
        return ResponseEntity.ok(availableCars);
    }
}
