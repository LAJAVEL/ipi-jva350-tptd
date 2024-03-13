package com.ipi.jva350.model;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EntrepriseTest {

    @Test
    public void testEstDansPlage() {
        LocalDate debutPlage = LocalDate.of(2024, 3, 1);
        LocalDate finPlage = LocalDate.of(2024, 3, 15);

        try {
            // verifie si le 5 mars 2024 est dans la plage
            assertTrue(Entreprise.estDansPlage(LocalDate.of(2024, 3, 5), debutPlage, finPlage));

            // verifie si le 20 mars 2024 n'est pas dans la plage
            assertFalse(Entreprise.estDansPlage(LocalDate.of(2024, 3, 20), debutPlage, finPlage));

            // verifie si le début de la plage est inclus
            assertTrue(Entreprise.estDansPlage(debutPlage, debutPlage, finPlage));

            // verifie si la fin de la plage est incluse
            assertTrue(Entreprise.estDansPlage(finPlage, debutPlage, finPlage));

            // verifier si un jour férié est considéré dans la plage
            assertFalse(Entreprise.estDansPlage(LocalDate.of(2024, 3, 8), debutPlage, finPlage)); // 8 mars 2024 est un jour férié
        } catch (Exception e) {
            System.err.println("je n'ai pas trouvé la source de cette erreur : " + e.getMessage());
            e.printStackTrace();
            fail("Une erreur s'est produite : " + e.getMessage());
        }
    }
}
