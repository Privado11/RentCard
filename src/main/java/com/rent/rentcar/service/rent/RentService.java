package com.rent.rentcar.service.rent;

import com.rent.rentcar.dto.rent.RentDto;
import com.rent.rentcar.dto.rent.RentToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;

import java.util.List;

public interface RentService {
    RentDto getRentById(Long id) throws NotFoundExceptionEntity;
    RentDto addRent(RentToSaveDto rentToSaveDto);
    RentDto updateRent(Long id, RentToSaveDto rentToSaveDto) throws NotFoundExceptionEntity;
    void deleteRent(Long id);
    List<RentDto> getAllRents();
    List<RentDto> getAllRentsByUserId(Long userId);
}
