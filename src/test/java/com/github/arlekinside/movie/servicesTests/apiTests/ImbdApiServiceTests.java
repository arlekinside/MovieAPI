package com.github.arlekinside.movie.servicesTests.apiTests;


import com.github.arlekinside.movie.services.api.ImbdApiService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;

import java.io.IOException;

@SpringBootTest
@Order(1)
public class ImbdApiServiceTests {

    @Autowired
    private ImbdApiService service;

    @Test
    public void whenFind_thenResultNotNull() {
        try {
            Assertions.assertNotNull(service.find("Inception", "en"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
