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
            em.getTransaction().commit();
        }
    }   
}