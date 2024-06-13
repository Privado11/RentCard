package com.rent.rentcar.service.reservation;

import com.rent.rentcar.dto.reservation.ReservationDto;
import com.rent.rentcar.dto.reservation.ReservationMapper;
import com.rent.rentcar.dto.reservation.ReservationToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.Reservation;
import com.rent.rentcar.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private ReservationMapper reservationMapper;

    @InjectMocks
    private RentServiceImpl rentService;

    private Reservation reservation;
    private ReservationDto reservationDto;

    @BeforeEach
    void setUp() {
        reservation = new Reservation(1L, null, null, null, null, null, null);
        reservationDto = new ReservationDto(1L, null, null, null, null, null);
    }

    @Test
    void testGetRentById() throws NotFoundExceptionEntity {
        Long rentId = 1L;
        when(reservationRepository.findById(rentId)).thenReturn(Optional.of(reservation));
        when(reservationMapper.toDto(reservation)).thenReturn(reservationDto);

        ReservationDto result = rentService.getRentById(rentId);

        assertNotNull(result);
        assertEquals(reservation.getId(), result.id());
    }

    @Test
    void testAddRent() {
        ReservationToSaveDto newRentDto = new ReservationToSaveDto(null, null, null, null, null, null);
        when(reservationMapper.saveDtoToEntity(newRentDto)).thenReturn(reservation);
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(reservationDto);

        ReservationDto result = rentService.addRent(newRentDto);

        assertNotNull(result);
        assertNotNull(result.id());
    }

    @Test
    void testUpdateRent() throws NotFoundExceptionEntity {
        Long rentId = 1L;
        ReservationToSaveDto updatedRentDto = new ReservationToSaveDto(rentId, null, null, null, null, null);
        when(reservationRepository.findById(rentId)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(reservation)).thenReturn(reservation);
        when(reservationMapper.toDto(reservation)).thenReturn(reservationDto);

        ReservationDto result = rentService.updateRent(rentId, updatedRentDto);

        assertNotNull(result);
        assertEquals(rentId, result.id());
    }

    @Test
    void testDeleteRent() {
        Long rentId = 1L;
        when(reservationRepository.findById(rentId)).thenReturn(Optional.of(reservation));
        rentService.deleteRent(rentId);

        verify(reservationRepository, times(1)).delete(reservation);
    }

    @Test
    void testGetAllRents() {
        List<Reservation> reservationList = List.of(new Reservation(1L, null, null, null, null, null, null),
                new Reservation(2L, null, null, null, null, null, null));
        when(reservationRepository.findAll()).thenReturn(reservationList);

        List<ReservationDto> result = rentService.getAllRents();

        assertNotNull(result);
        assertEquals(reservationList.size(), result.size());
    }

    @Test
    void testGetAllRentsByUserIdCard() {
        String userIdCard = "1234567";
        List<Reservation> reservationList = List.of(new Reservation(1L, null, null, null, null, null, null),
                new Reservation(2L, null, null, null, null, null, null));
        when(reservationRepository.findByUser_IdCard(userIdCard)).thenReturn(reservationList);


        List<ReservationDto> result = rentService.getAllRentsByUserIdCard(userIdCard);

        assertNotNull(result);
        assertEquals(reservationList.size(), result.size());
    }
}
