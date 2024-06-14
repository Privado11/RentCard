package com.rent.rentcar.dto.invoice;

import com.rent.rentcar.Enum.PaymentMethod;
import com.rent.rentcar.models.Reservation;

import java.time.LocalDateTime;

public record InvoiceToSaveDto(Long id,
                               Double totalAmount,
                               LocalDateTime paymentDate,
                               Reservation reservation,
                               PaymentMethod paymentMethod) {
}
