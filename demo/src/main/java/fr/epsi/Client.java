package fr.epsi;


import jakarta.persistence.*;

@Entity
@Table(name = "CLIENT")
public class Client {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom; 
    @ManyToOne
    @JoinColumn(name = "ID_EMPRUNT", referencedColumnName = "ID")
    private Emprunt emprunt;
    public Client() {
    }

    public Client(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public Integer getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
}
