package com.rent.rentcar.repository;

import com.rent.rentcar.models.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
        List<Car> findBySalesBranch_City_Name(String nameCity);
         List<Car> findByAvailableTrue();
   @Query("SELECT c FROM Car c WHERE c.salesBranch.city.id = :cityId AND c.id NOT IN (" +
        "SELECT r.car.id FROM Reservation r WHERE " +
        "(r.startDate BETWEEN :startDate AND :endDate) OR " +
        "(r.endDate BETWEEN :startDate AND :endDate) OR " +
        "(r.startDate <= :startDate AND r.endDate >= :endDate))")
    List<Car> findAvailableCarsInCity(@Param("cityId") Long cityId,
                                  @Param("startDate") LocalDateTime startDate,
                                  @Param("endDate") LocalDateTime endDate);

}
