package com.rent.rentcar.repository;

import com.rent.rentcar.models.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
}
