package fr.epsi;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App 
{
    public static void main(String[] args)
    {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu")) {
            System.out.println( emf );
    }
}
}
