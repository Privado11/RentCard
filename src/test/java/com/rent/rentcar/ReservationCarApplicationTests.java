package com.rent.rentcar;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ReservationCarApplicationTests {

    @Test
    void contextLoads() {
    }

}
