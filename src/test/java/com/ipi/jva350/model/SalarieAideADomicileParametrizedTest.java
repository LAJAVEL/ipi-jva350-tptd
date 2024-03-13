package com.ipi.jva350.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalarieAideADomicileParametrizedTest {

    @ParameterizedTest
    @CsvSource({
            "2023-12-17, 2023-12-28, 9",
            "2023-12-17, 2023-12-29, 11",
            "2023-12-17, 2023-12-30, 11",
    })
    public void testCalculeJoursDeCongeDecomptesPourPlage(String debutPlage, String finPlage, int expectedResult) {
        // Given
        SalarieAideADomicile monSalarie = new SalarieAideADomicile("Paul",
                LocalDate.of(2023, 6, 28), LocalDate.now(), 20,
                30, 4, 1,
                8);

        // When
        LinkedHashSet<LocalDate> datesSet = monSalarie.calculeJoursDeCongeDecomptesPourPlage(LocalDate.parse(debutPlage), LocalDate.parse(finPlage));
        int actualResult = datesSet.size();

        // Then
        assertEquals(expectedResult, actualResult);
    }
}