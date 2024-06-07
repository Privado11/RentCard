package com.rent.rentcar.repository;

import com.rent.rentcar.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
