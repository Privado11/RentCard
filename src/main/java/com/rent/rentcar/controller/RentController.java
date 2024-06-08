package com.rent.rentcar.controller;

import com.rent.rentcar.dto.rent.RentDto;
import com.rent.rentcar.dto.rent.RentToSaveDto;
import com.rent.rentcar.exception.NotFoundExceptionEntity;
import com.rent.rentcar.service.rent.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rents")
public class RentController {

    private final RentService rentService;

    @Autowired
    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping
    public ResponseEntity<List<RentDto>> getAllRents() {
        List<RentDto> rents = rentService.getAllRents();
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentDto> getRentById(@PathVariable Long id) {
        try {
            RentDto rentDto = rentService.getRentById(id);
            return ResponseEntity.ok(rentDto);
        } catch (NotFoundExceptionEntity ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<RentDto> saveRent(@RequestBody RentToSaveDto rentToSaveDto) {
        RentDto savedRent = rentService.addRent(rentToSaveDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RentDto> updateRent(@PathVariable Long id, @RequestBody RentToSaveDto rentToSaveDto) {
        try {
            RentDto updatedRent = rentService.updateRent(id, rentToSaveDto);
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
    public ResponseEntity<List<RentDto>> getAllRentsByUserId(@PathVariable String userIdCard) {
        List<RentDto> rentsByUserId = rentService.getAllRentsByUserIdCard(userIdCard);
        return ResponseEntity.ok(rentsByUserId);
    }
}
