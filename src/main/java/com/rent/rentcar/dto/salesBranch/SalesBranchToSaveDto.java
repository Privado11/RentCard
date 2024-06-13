package com.rent.rentcar.dto.salesBranch;

import com.rent.rentcar.Enum.DayWeek;
import com.rent.rentcar.models.City;

import java.time.LocalTime;
import java.util.Map;

public record SalesBranchToSaveDto(Long id,
                                   String name,
                                   String address,
                                   City city,
                                   Map<DayWeek, LocalTime> startTime,
                                   Map<DayWeek, LocalTime> endTime) {
}
