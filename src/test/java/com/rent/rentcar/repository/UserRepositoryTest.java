package com.rent.rentcar.repository;

import com.rent.rentcar.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class UserRepositoryTest extends AbstractIntegrationDBTest{

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void givenUser_whenSaveUser_thenUserIsSaved() {
        // Given
        User user = User.builder()
                .name("John")
                .lastName("Doe")
                .idCard("1234567")
                .address("123 Main St")
                .phone("555-1234")
                .build();

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotNull();
    }

    @Test
    void givenUser_whenFindById_thenReturnUser() {
        // Given
        User user = User.builder()
                .name("Jane")
                .lastName("Doe")
                .idCard("7654321")
                .address("456 Oak St")
                .phone("555-5678")
                .build();
        User savedUser = userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findById(savedUser.getId());

        // Then
        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getName()).isEqualTo("Jane");
    }

    @Test
    void givenUser_whenUpdateUser_thenUserIsUpdated() {
        // Given
        User user = User.builder()
                .name("Alice")
                .lastName("Smith")
                .idCard("9876543")
                .address("789 Elm St")
                .phone("555-7890")
                .build();
        User savedUser = userRepository.save(user);

        // When
        savedUser.setLastName("Johnson");
        User updatedUser = userRepository.save(savedUser);

        // Then
        assertThat(updatedUser.getLastName()).isEqualTo("Johnson");
    }

    @Test
    void givenUser_whenDeleteUser_thenUserIsDeleted() {
        // Given
        User user = User.builder()
                .name("Bob")
                .lastName("Brown")
                .idCard("8765432")
                .address("123 Elm St")
                .phone("555-6789")
                .build();
        User savedUser = userRepository.save(user);

        // When
        userRepository.deleteById(savedUser.getId());

        // Then
        Optional<User> deletedUser = userRepository.findById(savedUser.getId());
        assertThat(deletedUser).isNotPresent();
    }

}