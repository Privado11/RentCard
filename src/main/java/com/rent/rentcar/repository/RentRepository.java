package com.rent.rentcar.repository;


import com.rent.rentcar.models.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findByUser_IdCard(String userIdCard);



}
