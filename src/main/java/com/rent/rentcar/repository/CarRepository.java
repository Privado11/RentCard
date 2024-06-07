package com.rent.rentcar.repository;

import com.rent.rentcar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByCity_Name(String nameCity);
    List<Car> findByAvailableTrue();
}
