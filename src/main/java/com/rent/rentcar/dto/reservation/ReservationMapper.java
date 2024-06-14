package com.rent.rentcar.dto.reservation;

import com.rent.rentcar.models.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReservationMapper {
    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);

    ReservationDto toDto(Reservation reservation);
    Reservation saveDtoToEntity(ReservationToSaveDto reservationToSaveDto);
    Reservation toEntity(ReservationDto reservationDto);
}
