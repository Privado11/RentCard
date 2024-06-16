package com.rent.rentcar.controller;

import com.rent.rentcar.dto.reservation.ReservationDto;
import com.rent.rentcar.dto.reservation.ReservationToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.service.reservation.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {

    private final RentService rentService;

    @Autowired
    public ReservationController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public ResponseEntity<List<ReservationDto>> getAllRents() {
        List<ReservationDto> rents = rentService.getAllRents();
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDto> getRentById(@PathVariable Long id) {
        try {
            ReservationDto reservationDto = rentService.getRentById(id);
            return ResponseEntity.ok(reservationDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReservationDto> saveRent(@RequestBody ReservationToSaveDto reservationToSaveDto) {
        ReservationDto savedRent = rentService.addRent(reservationToSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDto> updateRent(@PathVariable Long id, @RequestBody ReservationToSaveDto reservationToSaveDto) {
        try {
            ReservationDto updatedRent = rentService.updateRent(id, reservationToSaveDto);
            return ResponseEntity.ok(updatedRent);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRent(@PathVariable Long id) {
        rentService.deleteRent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ReservationDto>> getAllRentsByUserId(@PathVariable String userId) {
        List<ReservationDto> rentsByUserId = rentService.getAllRentsByUserIdCard(userIdCard);
        return ResponseEntity.ok(rentsByUserId);
    }
}
