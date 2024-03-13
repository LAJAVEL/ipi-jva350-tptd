package com.ipi.jva350.model;

import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
public class SalarieAideADomicileServiceIntegrationTest {

    @Autowired
    private SalarieAideADomicileService salarieService;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void testAjouteConge() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile("Paul", LocalDate.of(2024, 2, 22),
                LocalDate.of(2024, 2, 22), 20, 30, 0, 1, 8);

    }
}
