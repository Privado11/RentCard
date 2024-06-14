package com.rent.rentcar.models;

import com.rent.rentcar.Enum.TransmissionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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

    @Column(length = 1000)
    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private TransmissionType transmissionType;

    @ManyToOne
    @JoinColumn(name = "sales_branches_id")
    private SalesBranch salesBranch;

    @OneToMany(mappedBy = "car")
    private List<Reservation> reservations;

}
