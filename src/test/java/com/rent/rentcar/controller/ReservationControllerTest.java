package com.rent.rentcar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rent.rentcar.dto.reservation.ReservationDto;
import com.rent.rentcar.dto.reservation.ReservationToSaveDto;
import com.rent.rentcar.service.reservation.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Collections;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservationController.class)
@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentService rentService;

    private ReservationDto reservationDto;
    private ReservationToSaveDto reservationToSaveDto;

    @BeforeEach
    void setUp() {
        reservationDto = new ReservationDto(1L, null, null, null, null, null);
        reservationToSaveDto = new ReservationToSaveDto(1L, null, null, null, null, null);
    }

    @Test
    void getAllRents() throws Exception {
        List<ReservationDto> rents = Collections.singletonList(reservationDto);
        when(rentService.getAllRents()).thenReturn(rents);

        mockMvc.perform(get("/api/v1/rents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void getRentById() throws Exception {
        when(rentService.getRentById(1L)).thenReturn(reservationDto);

        mockMvc.perform(get("/api/v1/rents/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void saveRent() throws Exception {
        when(rentService.addRent(reservationToSaveDto)).thenReturn(reservationDto);

        mockMvc.perform(post("/api/v1/rents")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reservationToSaveDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateRent() throws Exception {
        when(rentService.updateRent(1L, reservationToSaveDto)).thenReturn(reservationDto);

        mockMvc.perform(put("/api/v1/rents/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(reservationToSaveDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteRent() throws Exception {
        mockMvc.perform(delete("/api/v1/rents/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(rentService, times(1)).deleteRent(1L);
    }

}
