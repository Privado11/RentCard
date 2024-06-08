package com.rent.rentcar.service.rent;

import com.rent.rentcar.dto.rent.RentDto;
import com.rent.rentcar.dto.rent.RentMapper;
import com.rent.rentcar.dto.rent.RentToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.models.Rent;
import com.rent.rentcar.repository.RentRepository;
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
class RentServiceImplTest {

    @Mock
    private RentRepository rentRepository;

    @Mock
    private RentMapper rentMapper;

    @InjectMocks
    private RentServiceImpl rentService;

    private Rent rent;
    private RentDto rentDto;

    @BeforeEach
    void setUp() {
        rent = new Rent(1L, null, null, null, null, null);
        rentDto = new RentDto(1L, null, null, null, null, null);
    }

    @Test
    void testGetRentById() throws NotFoundExceptionEntity {
        Long rentId = 1L;
        when(rentRepository.findById(rentId)).thenReturn(Optional.of(rent));
        when(rentMapper.toDto(rent)).thenReturn(rentDto);

        RentDto result = rentService.getRentById(rentId);

        assertNotNull(result);
        assertEquals(rent.getId(), result.id());
    }

    @Test
    void testAddRent() {
        RentToSaveDto newRentDto = new RentToSaveDto(null, null, null, null, null, null);
        when(rentMapper.saveDtoToEntity(newRentDto)).thenReturn(rent);
        when(rentRepository.save(rent)).thenReturn(rent);
        when(rentMapper.toDto(rent)).thenReturn(rentDto);

        RentDto result = rentService.addRent(newRentDto);

        assertNotNull(result);
        assertNotNull(result.id());
    }

    @Test
    void testUpdateRent() throws NotFoundExceptionEntity {
        Long rentId = 1L;
        RentToSaveDto updatedRentDto = new RentToSaveDto(rentId, null, null, null, null, null);
        when(rentRepository.findById(rentId)).thenReturn(Optional.of(rent));
        when(rentRepository.save(rent)).thenReturn(rent);
        when(rentMapper.toDto(rent)).thenReturn(rentDto);

        RentDto result = rentService.updateRent(rentId, updatedRentDto);

        assertNotNull(result);
        assertEquals(rentId, result.id());
    }

    @Test
    void testDeleteRent() {
        Long rentId = 1L;
        when(rentRepository.findById(rentId)).thenReturn(Optional.of(rent));
        rentService.deleteRent(rentId);

        verify(rentRepository, times(1)).delete(rent);
    }

    @Test
    void testGetAllRents() {
        List<Rent> rentList = List.of(new Rent(1L, null, null, null, null, null),
                new Rent(2L, null, null, null, null, null));
        when(rentRepository.findAll()).thenReturn(rentList);

        List<RentDto> result = rentService.getAllRents();

        assertNotNull(result);
        assertEquals(rentList.size(), result.size());
    }

    @Test
    void testGetAllRentsByUserIdCard() {
        String userIdCard = "1234567";
        List<Rent> rentList = List.of(new Rent(1L, null, null, null, null, null),
                new Rent(2L, null, null, null, null, null));
        when(rentRepository.findByUser_IdCard(userIdCard)).thenReturn(rentList);


        List<RentDto> result = rentService.getAllRentsByUserIdCard(userIdCard);

        assertNotNull(result);
        assertEquals(rentList.size(), result.size());
    }
}
