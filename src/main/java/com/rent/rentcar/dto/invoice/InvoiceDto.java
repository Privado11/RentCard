package com.rent.rentcar.dto.invoice;

import com.rent.rentcar.Enum.PaymentMethod;
import com.rent.rentcar.dto.reservation.ReservationDto;

import java.time.LocalDateTime;

public record InvoiceDto(Long id,
                         Double totalAmount,
                         LocalDateTime paymentDate,
                         ReservationDto reservation,
                         PaymentMethod paymentMethod) {
}
