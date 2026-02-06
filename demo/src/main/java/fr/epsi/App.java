package fr.epsi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;


public class App 
{
    public static void main(String[] args)
    {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
            EntityManager em = emf.createEntityManager();
        ) {
            em.getTransaction().begin();
            
            Livre livre = em.find(Livre.class, 1);
            if (null != livre) {
                System.out.println("Livre : " + livre.getTitre() + " de " + livre.getAuteur());
            }

            //Livre livreNew = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry");
                //em.persist(livreNew);
            
            Livre livreAModifier = em.find(Livre.class, 5);
            if (null != livreAModifier) {
                livreAModifier.setTitre("Du plaisir dans la cuisine");
            }
            TypedQuery<Livre> queryTitre = em.createQuery(
                "SELECT l FROM Livre l WHERE l.titre = :titre", Livre.class);
            queryTitre.setParameter("titre", "Du plaisir dans la cuisine");
            for (Livre l : queryTitre.getResultList()) {
                System.out.println("Livre : " + l.getTitre() + " de " + l.getAuteur());
            }
            TypedQuery<Livre> queryAuteur = em.createQuery(
                "SELECT l FROM Livre l WHERE l.auteur = :auteur", Livre.class);
            queryAuteur.setParameter("auteur", "Emile Zola");
            for (Livre l : queryAuteur.getResultList()) {
                System.out.println("Livre : " + l.getTitre() + " de " + l.getAuteur());
            }
            //supprimmer de l'id 7 à 16
            Livre livreASupprimer = em.find(Livre.class, 6);
            if (null != livreASupprimer) {
                em.remove(livreASupprimer);
            }
            TypedQuery<Livre> queryAll = em.createQuery(
                "SELECT l FROM Livre l", Livre.class);
            for (Livre l : queryAll.getResultList()) {
                System.out.println("Livre de GetAll : " + l.getTitre() + " de " + l.getAuteur());
            }
            //Réalisez une requête qui permet d'extraire un emprunt et tous ses livres associés
            TypedQuery<Emprunt> queryEmprunt = em.createQuery(
                "SELECT e FROM Emprunt e JOIN e.livres WHERE e.id = 1", Emprunt.class);
            Emprunt emprunt = queryEmprunt.getSingleResult();
            System.out.println("Emprunt ID: " + emprunt.getId());
            for (Livre l : emprunt.getLivres()) {
                System.out.println("  - Livre: " + l.getTitre());
            }

            //Réalisez une requête qui permet d'extraire tous les emprunts d'un client donné
            TypedQuery<Emprunt> queryEmpruntsClient = em.createQuery(
                "SELECT e FROM Emprunt e WHERE e.client.id = 1", Emprunt.class);
            for (Emprunt e : queryEmpruntsClient.getResultList()) {
                System.out.println("Emprunt ID: " + e.getId());
            }
            em.getTransaction().commit();
        }
    }   
}