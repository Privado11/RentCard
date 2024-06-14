package com.rent.rentcar.service.reservation;

import com.rent.rentcar.dto.reservation.ReservationDto;
import com.rent.rentcar.dto.reservation.ReservationMapper;
import com.rent.rentcar.dto.reservation.ReservationToSaveDto;
import com.rent.rentcar.exception.NotAbleToDeleteException;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.Reservation;
import com.rent.rentcar.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService{

    private final ReservationRepository reservationRepository;
    private final ReservationMapper reservationMapper;

    @Autowired
    public RentServiceImpl(ReservationRepository reservationRepository, ReservationMapper reservationMapper) {
        this.reservationRepository = reservationRepository;
        this.reservationMapper = reservationMapper;
    }

    @Override
    public ReservationDto getRentById(Long id) throws NotFoundExceptionEntity {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotFoundExceptionEntity("Rent not found."));
        return reservationMapper.toDto(reservation);
    }

    @Override
    public ReservationDto addRent(ReservationToSaveDto reservationToSaveDto) {
        Reservation reservation = reservationMapper.saveDtoToEntity(reservationToSaveDto);
        return reservationMapper.toDto(reservationRepository.save(reservation));
    }

    @Override
    public ReservationDto updateRent(Long id, ReservationToSaveDto reservationToSaveDto) throws NotFoundExceptionEntity {
        return reservationRepository.findById(id)
                .map(rent -> {
                    rent.setStartDate(reservationToSaveDto.startDate());
                    rent.setEndDate(reservationToSaveDto.endDate());
                    rent.setCar(reservationToSaveDto.car());
                    rent.setUser(reservationToSaveDto.user());
                    rent.setTotalPrice(reservationToSaveDto.totalPrice());

                    Reservation reservationSaved = reservationRepository.save(rent);
                    return reservationMapper.toDto(reservationSaved);
                }).orElseThrow(() -> new NotFoundExceptionEntity("Rent not found."));
    }

    @Override
    public void deleteRent(Long id){
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new NotAbleToDeleteException("Rent not found."));
        reservationRepository.delete(reservation);

    }

    @Override
    public List<ReservationDto> getAllRents() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }

    @Override
    public List<ReservationDto> getAllRentsByUserIdCard(String userIdCard) {
        return reservationRepository.findByUser_IdCard(userIdCard)
                .stream()
                .map(reservationMapper::toDto)
                .toList();
    }
}
