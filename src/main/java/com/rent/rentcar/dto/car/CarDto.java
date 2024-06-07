package com.rent.rentcar.dto.car;

import com.rent.rentcar.dto.city.CityDto;

public record CarDto(Long id,
                     String brand,
                     String model,
                     String color,
                     String year,
                     String licensePlate,
                     Double price,
                     Boolean available,
                     String description,
                     CityDto city) {
}
