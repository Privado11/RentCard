package com.rent.rentcar.dto.reservation;

import com.rent.rentcar.dto.car.CarDto;
import com.rent.rentcar.dto.user.UserDto;


import java.time.LocalDateTime;

public record ReservationDto(Long id,
                             UserDto user,
                             CarDto car,
                             LocalDateTime startDate,
                             LocalDateTime endDate,
                             Double totalPrice) {
}
