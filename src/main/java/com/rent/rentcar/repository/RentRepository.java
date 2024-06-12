package com.rent.rentcar.repository;

import com.rent.rentcar.models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUser_IdCard(String userIdCard);
}
