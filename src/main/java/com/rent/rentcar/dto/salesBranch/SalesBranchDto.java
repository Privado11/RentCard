package com.rent.rentcar.dto.salesBranch;

import com.rent.rentcar.Enum.DayWeek;
import com.rent.rentcar.dto.city.CityDto;

import java.time.LocalTime;
import java.util.Map;

public record SalesBranchDto(Long id,
                             String name,
                             String address,
                             CityDto city,
                             Map<DayWeek, LocalTime> startTime,
                             Map<DayWeek, LocalTime> endTime){
}
