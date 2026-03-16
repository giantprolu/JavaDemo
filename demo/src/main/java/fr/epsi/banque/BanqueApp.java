package fr.epsi.banque;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BanqueApp {

public static void main(String[] args) {

        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("banque");
                EntityManager em = emf.createEntityManager()) {

                em.getTransaction().begin();

                // --- Banque ---
                Banque bnp = new Banque("BNP Paribas");
                em.persist(bnp);

                // --- Clients avec adresse ---
                Adresse adresse1 = new Adresse(12, "Rue de la Paix", 75001, "Paris");
                Client alice = new Client("Dupont", "Alice", LocalDate.of(1990, 5, 15));
                alice.setAdresse(adresse1);
                em.persist(alice);

                Adresse adresse2 = new Adresse(3, "Avenue des Fleurs", 44000, "Nantes");
                Client bob = new Client("Martin", "Bob", LocalDate.of(1985, 11, 30));
                bob.setAdresse(adresse2);
                em.persist(bob);

                // --- Comptes ---
                LivretA livretAlice = new LivretA("LA-001", 1500.0, 3.0);
                livretAlice.setBanque(bnp);
                livretAlice.setClient(alice);
                em.persist(livretAlice);

                AssuranceVie avBob = new AssuranceVie("AV-002", 50000.0,
                        LocalDate.of(2035, 12, 31), 4.5);
                avBob.setBanque(bnp);
                avBob.setClient(bob);
                em.persist(avBob);

                Compte compteCourant = new Compte("CC-003", 2000.0);
                compteCourant.setBanque(bnp);
                compteCourant.setClient(alice);
                em.persist(compteCourant);

                // --- Opérations ---
                Operation depot = new Operation(LocalDateTime.now(), 500.0, "Dépôt initial");
                depot.setCompte(livretAlice);
                em.persist(depot);

                Virement virement = new Virement(
                        LocalDateTime.now(), 200.0, "Loyer", "Jean Durand");
                virement.setCompte(compteCourant);
                em.persist(virement);

                em.getTransaction().commit();

                System.out.println("Données insérées avec succès !");
                System.out.println("Banque  : " + bnp.getNom() + " (id=" + bnp.getId() + ")");
                System.out.println("Client  : " + alice.getPrenom() + " " + alice.getNom()
                        + " né(e) le " + alice.getDateNaissance());
                System.out.println("LivretA : " + livretAlice.getNumero()
                        + " solde=" + livretAlice.getSolde() + " taux=" + livretAlice.getTaux() + "%");
                System.out.println("Virement vers : " + virement.getBeneficiaire()
                        + " montant=" + virement.getMontant());
                }
        }
}