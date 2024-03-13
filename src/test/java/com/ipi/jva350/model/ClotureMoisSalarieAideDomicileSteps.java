package com.ipi.jva350.model;

import com.ipi.jva350.model.SalarieAideADomicile;
import com.ipi.jva350.service.SalarieAideADomicileService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Component // Ajout de l'annotation @Component pour que la classe soit un bean Spring
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
        salarie = new SalarieAideADomicile();
        salarie.setNom("Paul");
        salarie.setJoursTravailles(joursTravailles); // Correction : appel de la méthode setJoursTravailles
        salarie.setJoursCongesPayes(joursCongesPayes); // Correction : appel de la méthode setJoursCongesPayes
    }

    @When("le mois est clôturé")
    public void le_mois_est_cloture() {
        // Implémentez ici la logique de clôture du mois
    }

    @Then("le solde de congés payés est remis à {int}")
    public void le_solde_de_conges_payes_est_remis_a(int soldeAttendu) {
        // Vérifiez le solde des congés payés ici
    }
}
