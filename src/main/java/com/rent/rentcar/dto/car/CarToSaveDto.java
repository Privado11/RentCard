package com.rent.rentcar.dto.car;

import com.rent.rentcar.models.City;

public record CarToSaveDto(Long id,
                           String brand,
                           String model,
                           String color,
                           String year,
                           String licensePlate,
                           Double price,
                           Boolean available,
                           String description,
                           City city) {
}
