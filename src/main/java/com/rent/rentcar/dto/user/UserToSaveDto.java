package com.rent.rentcar.dto.user;

public record UserToSaveDto(Long id,
                            String name,
                            String lastName,
                            String email,
                            String address,
                            String phone) {
}
