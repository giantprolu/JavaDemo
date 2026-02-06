package fr.epsi;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPRUNT")
public class Emprunt {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "DATE_DEBUT")
    private LocalDateTime date_debut;
    @Column(name = "DATE_FIN")
    private LocalDateTime date_fin; 
    @Column(name = "DELAI")
    private Integer delai;

    @ManyToMany
    @JoinTable(name = "COMPO",
        joinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID"),
        inverseJoinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID")
    )
    private java.util.Set<Livre> livres;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENT", referencedColumnName = "ID")
    private Client client;

    public Emprunt() {
    }

    public Emprunt(LocalDateTime date_debut, LocalDateTime date_fin) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }

    public Integer getId() {
        return id;
    }
    public LocalDateTime getDate_debut() {
        return date_debut;
    }
    public LocalDateTime getDate_fin() {
        return date_fin;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setDate_debut(LocalDateTime date_debut) {
        this.date_debut = date_debut;
    }
    public void setDate_fin(LocalDateTime date_fin) {
        this.date_fin = date_fin;
    }
    public java.util.Set<Livre> getLivres() {
        return livres;
    }
    public Client getClient() {
        return client;
    }
}
