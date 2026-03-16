package fr.epsi.banque;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "COMPTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NUMERO", unique = true, nullable = false)
    private String numero;

    @Column(name = "SOLDE")
    private double solde;

    @ManyToOne
    @JoinColumn(name = "BANQUE_ID", nullable = false)
    private Banque banque;

    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "compte", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Operation> operations;

    public Compte() {
    }

    public Compte(String numero, double solde) {
        this.numero = numero;
        this.solde = solde;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public double getSolde() { return solde; }
    public void setSolde(double solde) { this.solde = solde; }

    public Banque getBanque() { return banque; }
    public void setBanque(Banque banque) { this.banque = banque; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public List<Operation> getOperations() { return operations; }
    public void setOperations(List<Operation> operations) { this.operations = operations; }
}
