package com.rent.rentcar.dto.user;

public record UserDto(Long id,
                      String name,
                      String lastName,
                      String idCard,
                      String address,
                      String phone) {
}
