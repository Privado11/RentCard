package com.rent.rentcar.models;

import com.rent.rentcar.Enum.DayWeek;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "sales_branches")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SalesBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "city_id", nullable = false)
    private City city;

    private String address;


    private String name;

    @ElementCollection
    @CollectionTable(name = "hours_attention", joinColumns = @JoinColumn(name = "sales_branches_id"))
    @MapKeyColumn(name = "day_week")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DayWeek, LocalTime> startTime;

    @ElementCollection
    @CollectionTable(name = "hours_attention", joinColumns = @JoinColumn(name = "sales_branches_id"))
    @MapKeyColumn(name = "day_week")
    @MapKeyEnumerated(EnumType.STRING)
    private Map<DayWeek, LocalTime> endTime;

    @OneToMany(mappedBy = "salesBranch")
    private List<Car> cars;
}
