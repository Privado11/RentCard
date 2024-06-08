package com.rent.rentcar.service.rent;

import com.rent.rentcar.dto.rent.RentDto;
import com.rent.rentcar.dto.rent.RentMapper;
import com.rent.rentcar.dto.rent.RentToSaveDto;
import com.rent.rentcar.exception.NotAbleToDeleteException;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.Rent;
import com.rent.rentcar.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService{

    private final RentRepository rentRepository;
    private final RentMapper rentMapper;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository, RentMapper rentMapper) {
        this.rentRepository = rentRepository;
        this.rentMapper = rentMapper;
    }

    @Override
    public RentDto getRentById(Long id) throws NotFoundExceptionEntity {
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("Rent not found."));
        return rentMapper.toDto(rent);
    }

    @Override
    public RentDto addRent(RentToSaveDto rentToSaveDto) {
        Rent rent = rentMapper.saveDtoToEntity(rentToSaveDto);
        return rentMapper.toDto(rentRepository.save(rent));
    }

    @Override
    public RentDto updateRent(Long id, RentToSaveDto rentToSaveDto) throws NotFoundExceptionEntity {
        return rentRepository.findById(id)
                .map(rent -> {
                    rent.setStartDate(rentToSaveDto.startDate());
                    rent.setEndDate(rentToSaveDto.endDate());
                    rent.setCar(rentToSaveDto.car());
                    rent.setUser(rentToSaveDto.user());
                    rent.setTotalPrice(rentToSaveDto.totalPrice());

                    Rent rentSaved = rentRepository.save(rent);
                    return rentMapper.toDto(rentSaved);
                }).orElseThrow(() -> new NotFoundExceptionEntity("Rent not found."));
    }

    @Override
    public void deleteRent(Long id){
        Rent rent = rentRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Rent not found."));
        rentRepository.delete(rent);

    }

    @Override
    public List<RentDto> getAllRents() {
        return rentRepository.findAll()
                .stream()
                .map(rentMapper::toDto)
                .toList();
    }

    @Override
    public List<RentDto> getAllRentsByUserIdCard(String userIdCard) {
        return rentRepository.findByUser_IdCard(userIdCard)
                .stream()
                .map(rentMapper::toDto)
                .toList();
    }
}
