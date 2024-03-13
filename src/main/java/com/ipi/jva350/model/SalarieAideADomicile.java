package com.ipi.jva350.model;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

@Entity
public class SalarieAideADomicile {

    public static float CONGES_PAYES_ACQUIS_PAR_MOIS = 2.5f;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nom;

    public static List<DayOfWeek> joursHabituellementTravailles = new ArrayList<DayOfWeek>();

    static {
        joursHabituellementTravailles.add(DayOfWeek.MONDAY);
        joursHabituellementTravailles.add(DayOfWeek.TUESDAY);
        joursHabituellementTravailles.add(DayOfWeek.WEDNESDAY);
        joursHabituellementTravailles.add(DayOfWeek.THURSDAY);
        joursHabituellementTravailles.add(DayOfWeek.FRIDAY);
    }

    private LocalDate moisEnCours;
    private LocalDate moisDebutContrat;

    private double joursTravaillesAnneeN = 0;
    private double congesPayesAcquisAnneeN = 0;

    @Convert(converter = LinkedHashSetStringConverter.class)
    @Column
    private LinkedHashSet<LocalDate> congesPayesPris = new LinkedHashSet<LocalDate>();
    private double joursTravaillesAnneeNMoins1 = 0;
    private double congesPayesAcquisAnneeNMoins1 = 0;
    private double congesPayesPrisAnneeNMoins1 = 0;
    private int joursTravailles;
    private int joursCongesPayes;

    public SalarieAideADomicile() {
    }

    public SalarieAideADomicile(String nom, LocalDate moisDebutContrat, LocalDate moisEnCours,
                                double joursTravaillesAnneeN, double congesPayesAcquisAnneeN,
                                double joursTravaillesAnneeNMoins1, double congesPayesAcquisAnneeNMoins1, double congesPayesPrisAnneeNMoins1) {
        this.nom = nom;
        this.moisDebutContrat = moisDebutContrat;
        this.moisEnCours = moisEnCours;
        this.joursTravaillesAnneeNMoins1 = joursTravaillesAnneeNMoins1;
        this.congesPayesAcquisAnneeNMoins1 = congesPayesAcquisAnneeNMoins1;
        this.congesPayesPrisAnneeNMoins1 = congesPayesPrisAnneeNMoins1;
        this.joursTravaillesAnneeN = joursTravaillesAnneeN;
        this.congesPayesAcquisAnneeN = congesPayesAcquisAnneeN;
    }

    public boolean aLegalementDroitADesCongesPayes() {
        return this.getJoursTravaillesAnneeNMoins1() >= 10;
    }

    public boolean ajouteConge(LocalDate debutConge, LocalDate finConge) {
        return true;
    }

    public LinkedHashSet<LocalDate> calculeJoursDeCongeDecomptesPourPlage(LocalDate dateDebut, LocalDate dateFin) {
        LinkedHashSet<LocalDate> joursDeCongeDecomptes = new LinkedHashSet<>();

        if (dateDebut.isAfter(dateFin)) {
            return joursDeCongeDecomptes;
        }

        LocalDate dernierJourDeCongePris = this.getCongesPayesPris().isEmpty() ? null
                : this.getCongesPayesPris().stream().reduce((first, second) -> second).get();

        dateDebut = (dernierJourDeCongePris == null || dernierJourDeCongePris.isAfter(dateDebut)) ?
                dateDebut : dateDebut.plusDays(1);

        LocalDate jour = dateDebut;
        if (dateDebut.getDayOfWeek().getValue() != DayOfWeek.SUNDAY.getValue()
                && !Entreprise.estJourFerie(dateDebut) && estHabituellementTravaille(dateDebut)) {
            joursDeCongeDecomptes.add(dateDebut);
        }
        for (jour = jour.plusDays(1); jour.minusDays(1).isBefore(dateFin)
                || (!estHabituellementTravaille(jour) && estJourOuvrable(jour));
             jour = jour.plusDays(1)) {
            if (jour.getDayOfWeek().getValue() != DayOfWeek.SUNDAY.getValue()
                    && !Entreprise.estJourFerie(jour)) {
                joursDeCongeDecomptes.add(jour);
            }
        }
        return joursDeCongeDecomptes;
    }

    public boolean estJourOuvrable(LocalDate jour) {
        return jour.getDayOfWeek().getValue() != DayOfWeek.SUNDAY.getValue()
                && !Entreprise.estJourFerie(jour);
    }

    public boolean estHabituellementTravaille(LocalDate jour) {
        return joursHabituellementTravailles.contains(jour.getDayOfWeek());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public SalarieAideADomicile setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public double getJoursTravaillesAnneeN() {
        return joursTravaillesAnneeN;
    }

    public void setJoursTravaillesAnneeN(double joursTravaillesAnneeN) {
        this.joursTravaillesAnneeN = joursTravaillesAnneeN;
    }

    public double getCongesPayesAcquisAnneeN() {
        return congesPayesAcquisAnneeN;
    }

    public void setCongesPayesAcquisAnneeN(double congesPayesAcquisAnneeN) {
        this.congesPayesAcquisAnneeN = congesPayesAcquisAnneeN;
    }

    public LinkedHashSet<LocalDate> getCongesPayesPris() {
        return congesPayesPris;
    }

    public void setCongesPayesPris(LinkedHashSet<LocalDate> congesPayesPris) {
        this.congesPayesPris = congesPayesPris;
    }

    public double getJoursTravaillesAnneeNMoins1() {
        return joursTravaillesAnneeNMoins1;
    }

    public void setJoursTravaillesAnneeNMoins1(double joursTravaillesAnneeNMoins1) {
        this.joursTravaillesAnneeNMoins1 = joursTravaillesAnneeNMoins1;
    }

    public double getCongesPayesAcquisAnneeNMoins1() {
        return congesPayesAcquisAnneeNMoins1;
    }

    public void setCongesPayesAcquisAnneeNMoins1(double congesPayesAcquisAnneeNMoins1) {
        this.congesPayesAcquisAnneeNMoins1 = congesPayesAcquisAnneeNMoins1;
    }

    public double getCongesPayesPrisAnneeNMoins1() {
        return congesPayesPrisAnneeNMoins1;
    }

    public void setCongesPayesPrisAnneeNMoins1(double congesPayesPrisAnneeNMoins1) {
        this.congesPayesPrisAnneeNMoins1 = congesPayesPrisAnneeNMoins1;
    }

    public LocalDate getMoisEnCours() {
        return moisEnCours;
    }

    public void setMoisEnCours(LocalDate moisEnCours) {
        this.moisEnCours = moisEnCours;
    }

    public LocalDate getMoisDebutContrat() {
        return moisDebutContrat;
    }

    public void setMoisDebutContrat(LocalDate moisDebutContrat) {
        this.moisDebutContrat = moisDebutContrat;
    }

    public int getJoursTravailles() {
        return joursTravailles;
    }

    public int getJoursCongesPayes() {
        return joursCongesPayes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SalarieAideADomicile)) return false;
        SalarieAideADomicile s = (SalarieAideADomicile) o;
        return Objects.equals(id, s.id) &&
                Objects.equals(nom, s.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nom);
    }

    public void setJoursTravailles(int joursTravailles) {
        this.joursTravailles = joursTravailles;
    }

    public void setJoursCongesPayes(int joursCongesPayes) {
        this.joursCongesPayes = joursCongesPayes;
    }

    public double getCongesPayesRestantAnneeNMoins1() {
        return this.congesPayesAcquisAnneeNMoins1 - this.getCongesPayesPrisAnneeNMoins1();
    }
    /*
    public double getCongesPayesRestantAnneeNMoins1() {
        return congesPayesRestantAnneeNMoins1;
    }

    public void setCongesPayesRestantAnneeNMoins1(double congesPayesRestantAnneeNMoins1) {
        this.congesPayesRestantAnneeNMoins1 = congesPayesRestantAnneeNMoins1;
    }
    */
}
