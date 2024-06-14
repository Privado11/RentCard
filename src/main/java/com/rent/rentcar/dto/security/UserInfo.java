package com.rent.rentcar.dto.security;

import java.util.List;
import com.rent.rentcar.models.Role;

public record UserInfo(String name,
String lastName,
String idCard,
String email,
String address,
String phone,
String password,
List<Role> role ) {

}
