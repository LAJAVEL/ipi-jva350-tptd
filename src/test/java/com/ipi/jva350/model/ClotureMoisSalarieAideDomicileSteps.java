package com.ipi.jva350.steps;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.service.SalarieAideADomicileService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClotureMoisSalarieAideDomicileSteps {

    private SalarieAideADomicile salarie;
    private int joursTravailles;
    private int joursCongesPayes;

    @Autowired
    private SalarieAideADomicileService salarieService;

    @Given("un salarié avec {int} jours travaillés et {int} jours de congés payés")
    public void un_salarie_avec_jours_travailles_et_jours_conges_payes(int joursTravailles, int joursCongesPayes) {
        this.joursTravailles = joursTravailles;
        this.joursCongesPayes = joursCongesPayes;
        salarie = salarieService.creerSalarieAideADomicile("Paul", joursTravailles, joursCongesPayes);
    }

    @When("le mois est clôturé")
    public void le_mois_est_cloture() {
        salarieService.cloturerMoisSalarieAideADomicile(salarie);
    }

    @Then("le solde de congés payés est remis à {int}")
    public void le_solde_de_conges_payes_est_remis_a(int soldeAttendu) {
        assertEquals(soldeAttendu, salarie.getSoldeCongesPayes());
    }
}
