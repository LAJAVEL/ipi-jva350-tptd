package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SalarieAideADomicileUnitTest {

    @Test
    public void testALegalementDroitADesCongesPayesAucunJourTravaille() {
        SalarieAideADomicile salarie = new SalarieAideADomicile("Paul", LocalDate.of(2024, 2, 22),
                LocalDate.of(2024, 2, 22), 20, 30, 0, 1, 8);
        assertFalse(salarie.aLegalementDroitADesCongesPayes(), "Aucun jour travaillé l'année précédente, pas de droit à congés payés.");
    }

    @Test
    public void testALegalementDroitADesCongesPayesMoinsDeDixJoursTravailles() {
        SalarieAideADomicile salarie = new SalarieAideADomicile("Paul", LocalDate.of(2024, 2, 22),
                LocalDate.of(2024, 2, 22), 20, 30, 1, 1, 8);
        assertFalse(salarie.aLegalementDroitADesCongesPayes(), "Moins de 10 jours travaillés l'année précédente, pas de droit à congés payés.");
    }

    @Test
    public void testALegalementDroitADesCongesPayesDixJoursTravailles() {
        SalarieAideADomicile salarie = new SalarieAideADomicile("Paul", LocalDate.of(2024, 2, 22),
                LocalDate.of(2024, 2, 22), 20, 30, 10, 1, 8);
        assertTrue(salarie.aLegalementDroitADesCongesPayes(), "Dix jours travaillés l'année précédente, droit à congés payés.");
    }

    @Test
    public void testALegalementDroitADesCongesPayesPlusDeDixJoursTravailles() {
        SalarieAideADomicile salarie = new SalarieAideADomicile("Paul", LocalDate.of(2024, 2, 22),
                LocalDate.of(2024, 2, 22), 20, 30, 11, 1, 8);
        assertTrue(salarie.aLegalementDroitADesCongesPayes(), "Plus de dix jours travaillés l'année précédente, droit à congés payés.");
    }


}
