package com.rent.rentcar.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String model;
    private String color;
    private String year;

    @Column(unique = true)
    private String licensePlate;

    private Double price;
    private boolean available;
    private String description;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;
}
