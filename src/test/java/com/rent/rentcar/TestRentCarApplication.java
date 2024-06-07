package com.rent.rentcar;

import org.springframework.boot.SpringApplication;

public class TestRentCarApplication {

    public static void main(String[] args) {
        SpringApplication.from(RentCarApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
