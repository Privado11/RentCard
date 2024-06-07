package com.rent.rentcar.dto.rent;

import com.rent.rentcar.dto.car.CarDto;
import com.rent.rentcar.dto.user.UserDto;


import java.time.LocalDateTime;

public record RentDto(Long id,
                      UserDto user,
                      CarDto car,
                      LocalDateTime startDate,
                      LocalDateTime endDate,
                      Double totalPrice) {
}
