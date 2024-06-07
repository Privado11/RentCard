package com.rent.rentcar.service.city;

import com.rent.rentcar.dto.city.CityDto;
import com.rent.rentcar.dto.city.CityMapper;
import com.rent.rentcar.dto.city.CityToSaveDto;
import com.rent.rentcar.exception.NotAbleToDeleteException;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.City;
import com.rent.rentcar.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CityMapper cityMapper) {
        this.cityRepository = cityRepository;
        this.cityMapper = cityMapper;
    }

    @Override
    public CityDto getCityById(Long id) throws NotFoundExceptionEntity {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("City not found."));
        return cityMapper.toDto(city);
    }

    @Override
    public CityDto addCity(CityToSaveDto cityToSaveDto) {
        City city = cityMapper.saveDtoToEntity(cityToSaveDto);
        return cityMapper.toDto(cityRepository.save(city));
    }

    @Override
    public CityDto updateCity(Long id, CityToSaveDto cityToSaveDto) throws NotFoundExceptionEntity {
        return cityRepository.findById(id)
                .map(city -> {
                    city.setName(cityToSaveDto.name());
                    City citySaved = cityRepository.save(city);
                    return cityMapper.toDto(citySaved);
                }).orElseThrow(() -> new NotFoundExceptionEntity("City not found."));
    }

    @Override
    public void deleteCity(Long id){
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("City not found."));
        cityRepository.delete(city);
    }

    @Override
    public List<CityDto> getAllCities() {
        return cityRepository.findAll()
                .stream()
                .map(cityMapper::toDto)
                .toList();
    }
}
