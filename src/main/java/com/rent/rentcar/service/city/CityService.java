package com.rent.rentcar.service.city;

import com.rent.rentcar.dto.city.CityDto;
import com.rent.rentcar.dto.city.CityToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;

import java.util.List;

public interface CityService {
    CityDto getCityById(Long id) throws NotFoundExceptionEntity;
    CityDto addCity(CityToSaveDto cityToSaveDto);
    CityDto updateCity(Long id, CityToSaveDto cityToSaveDto) throws NotFoundExceptionEntity;
    void deleteCity(Long id);
    List<CityDto> getAllCities();
}
