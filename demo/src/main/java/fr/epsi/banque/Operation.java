package fr.epsi.banque;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "OPERATION")
@Inheritance(strategy = InheritanceType.JOINED)
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DATE_OPERATION", nullable = false)
    private LocalDateTime date;

    @Column(name = "MONTANT", nullable = false)
    private double montant;

    @Column(name = "MOTIF")
    private String motif;

    @ManyToOne
    @JoinColumn(name = "COMPTE_ID", nullable = false)
    private Compte compte;

    public Operation() {
    }

    public Operation(LocalDateTime date, double montant, String motif) {
        this.date = date;
        this.montant = montant;
        this.motif = motif;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public double getMontant() { return montant; }
    public void setMontant(double montant) { this.montant = montant; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public Compte getCompte() { return compte; }
    public void setCompte(Compte compte) { this.compte = compte; }
}
