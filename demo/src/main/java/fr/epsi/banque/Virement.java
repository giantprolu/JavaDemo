package fr.epsi.banque;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "VIREMENT")
public class Virement extends Operation {

    @Column(name = "BENEFICIAIRE")
    private String beneficiaire;

    public Virement() {
    }

    public Virement(LocalDateTime date, double montant, String motif, String beneficiaire) {
        super(date, montant, motif);
        this.beneficiaire = beneficiaire;
    }

    public String getBeneficiaire() { return beneficiaire; }
    public void setBeneficiaire(String beneficiaire) { this.beneficiaire = beneficiaire; }
}
