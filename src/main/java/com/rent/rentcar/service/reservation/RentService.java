package com.rent.rentcar.service.reservation;

import com.rent.rentcar.dto.reservation.ReservationDto;
import com.rent.rentcar.dto.reservation.ReservationToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;

import java.util.List;

public interface RentService {
    ReservationDto getRentById(Long id) throws NotFoundExceptionEntity;
    ReservationDto addRent(ReservationToSaveDto reservationToSaveDto);
    ReservationDto updateRent(Long id, ReservationToSaveDto reservationToSaveDto) throws NotFoundExceptionEntity;
    void deleteRent(Long id);
    List<ReservationDto> getAllRents();
    List<ReservationDto> getAllRentsByUserIdCard(String userIdCard);
}
