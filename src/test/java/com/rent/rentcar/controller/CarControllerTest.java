package com.rent.rentcar.controller;

import com.rent.rentcar.models.City;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rent.rentcar.dto.car.CarDto;
import com.rent.rentcar.dto.car.CarToSaveDto;
import com.rent.rentcar.service.car.CarService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CarController.class)
@ExtendWith(MockitoExtension.class)
class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    private CarDto carDto;
    private CarToSaveDto carToSaveDto;

    @BeforeEach
    void setUp() {
        carDto = new CarDto(1L, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", null);
        carToSaveDto = new CarToSaveDto(1l, "Toyota", "Corolla", "Black", "2022", "ABC123", 100.0, true, "Description", City.builder().build());    }

    @Test
    void getAllCars() throws Exception {
        List<CarDto> cars = Collections.singletonList(carDto);
        when(carService.getAllCars()).thenReturn(cars);

        mockMvc.perform(get("/api/v1/cars"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    void getCarById() throws Exception {
        when(carService.getCarById(1L)).thenReturn(carDto);

        mockMvc.perform(get("/api/v1/cars/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void saveCar() throws Exception {
        when(carService.addCar(carToSaveDto)).thenReturn(carDto);

        mockMvc.perform(post("/api/v1/cars")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(carToSaveDto)))
                .andExpect(status().isCreated());
    }

    @Test
    void updateCar() throws Exception {
        when(carService.updateCar(1L, carToSaveDto)).thenReturn(carDto);

        mockMvc.perform(put("/api/v1/cars/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(carToSaveDto)))
                .andExpect(status().isOk());
    }

    @Test
    void deleteCar() throws Exception {
        mockMvc.perform(delete("/api/v1/cars/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(carService, times(1)).deleteCar(1L);
    }

    @Test
    void getAllAvailableCars() throws Exception {
        List<CarDto> availableCars = Collections.singletonList(carDto);
        when(carService.getAllAvailableCars()).thenReturn(availableCars);

        mockMvc.perform(get("/api/v1/cars/available"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1));
    }

//    @Test
//    void getAllCarsByCityName() throws Exception {
//        List<CarDto> carsByCity = Collections.singletonList(carDto);
//        String city = "Los Angeles";
//        when(carService.getAllCarsByCityName(city)).thenReturn(carsByCity);
//
//        mockMvc.perform(get("/api/v1/cars/city/{city}", city))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].id").value(1));
//    }
}