package fr.epsi.banque;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ASSURANCE_VIE")
public class AssuranceVie extends Compte {

    @Column(name = "DATE_FIN")
    private LocalDate dateFin;

    @Column(name = "TAUX")
    private double taux;

    public AssuranceVie() {
    }

    public AssuranceVie(String numero, double solde, LocalDate dateFin, double taux) {
        super(numero, solde);
        this.dateFin = dateFin;
        this.taux = taux;
    }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }
}
