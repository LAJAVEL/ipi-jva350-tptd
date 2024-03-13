package com.ipi.jva350.model;

import com.ipi.jva350.repository.SalarieAideADomicileRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SalarieAideADomicileRepositoryIntegrationTest {

    @Autowired
    private SalarieAideADomicileRepository salarieRepository;

    @Test
    public void testFindByNom() {
        // Given
        SalarieAideADomicile salarie = new SalarieAideADomicile("Paul", LocalDate.of(2024, 2, 22),
                LocalDate.of(2024, 2, 22), 20, 30, 0, 1, 8);
        salarieRepository.save(salarie);

        // When
        SalarieAideADomicile foundSalarie = salarieRepository.findByNom("Paul");

        // Then
        assertEquals(salarie.getNom(), foundSalarie.getNom());

    }
}
