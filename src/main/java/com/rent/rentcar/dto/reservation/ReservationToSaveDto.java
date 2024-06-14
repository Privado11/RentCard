package com.rent.rentcar.dto.reservation;

import com.rent.rentcar.models.Car;
import com.rent.rentcar.models.User;

import java.time.LocalDateTime;

public record ReservationToSaveDto(Long id,
                                   User user,
                                   Car car,
                                   LocalDateTime startDate,
                                   LocalDateTime endDate,
                                   Double totalPrice) {
}
