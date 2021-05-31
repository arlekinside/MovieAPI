package com.github.arlekinside.movie;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class MovieApplicationTests {

    @Test
    void contextLoads() {
    }

}
