package com.rent.rentcar.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rents")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private Double totalPrice;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne()
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @OneToOne(mappedBy = "reservation")
    private Invoice invoice;

}
