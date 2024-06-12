package com.rent.rentcar.service.rent;

import com.rent.rentcar.dto.reservation.ReservationDto;
import com.rent.rentcar.dto.reservation.ReservationMapper;
import com.rent.rentcar.dto.reservation.ReservationToSaveDto;
import com.rent.rentcar.exception.NotAbleToDeleteException;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.Reservation;
import com.rent.rentcar.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService{

    private final RentRepository rentRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public RentServiceImpl(RentRepository rentRepository, ReservationMapper reservationMapper) {
        this.rentRepository = rentRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationDto getRentById(Long id) throws NotFoundExceptionEntity {
        Reservation reservation = rentRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("Rent not found."));
        return reservationMapper.toDto(reservation);
    }

    @Override
    public ReservationDto addRent(ReservationToSaveDto reservationToSaveDto) {
        Reservation reservation = reservationMapper.saveDtoToEntity(reservationToSaveDto);
        return reservationMapper.toDto(rentRepository.save(reservation));
    }

    @Override
    public ReservationDto updateRent(Long id, ReservationToSaveDto reservationToSaveDto) throws NotFoundExceptionEntity {
        return rentRepository.findById(id)
                .map(rent -> {
                    rent.setStartDate(reservationToSaveDto.startDate());
                    rent.setEndDate(reservationToSaveDto.endDate());
                    rent.setCar(reservationToSaveDto.car());
                    rent.setUser(reservationToSaveDto.user());
                    rent.setTotalPrice(reservationToSaveDto.totalPrice());

                    Reservation reservationSaved = rentRepository.save(rent);
                    return reservationMapper.toDto(reservationSaved);
                }).orElseThrow(() -> new NotFoundExceptionEntity("Rent not found."));
    }

    @Override
    public void deleteRent(Long id){
        Reservation reservation = rentRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Rent not found."));
        rentRepository.delete(reservation);

    }

    @Override
    public List<ReservationDto> getAllRents() {
        return rentRepository.findAll()
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Override
    public List<ReservationDto> getAllRentsByUserIdCard(String userIdCard) {
        return rentRepository.findByUser_IdCard(userIdCard)
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }
}
