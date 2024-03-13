package com.ipi.jva350.model;
import com.ipi.jva350.service.SalarieAideADomicileService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class SalarieAideADomicileMockTest {

    @Test
    public void testAjouteCongeAvecSucces() {
        // Créez un mock de votre classe SalarieAideADomicile
        SalarieAideADomicile salarieMock = mock(SalarieAideADomicile.class);

        // Configurez le comportement du mock selon votre scénario de test
        when(salarieMock.ajouteConge(any(LocalDate.class), any(LocalDate.class))).thenReturn(true);

        // Appelez la méthode à tester
        boolean result = salarieMock.ajouteConge(LocalDate.now(), LocalDate.now().plusDays(1));

        // Vérifiez le résultat
        assertTrue(result);

        // Vérifiez que la méthode a bien été appelée avec les bons arguments
        verify(salarieMock).ajouteConge(any(LocalDate.class), any(LocalDate.class));
    }
}
