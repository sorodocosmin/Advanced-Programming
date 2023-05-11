package org.example.connections;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ManagerConnectionJPA {
    private static EntityManagerFactory emf;

    private ManagerConnectionJPA() {
    }

    public static EntityManager getEntityManager(){
        if (emf == null){
            createEntityManager();
        }

        return emf.createEntityManager();
    }

    private static void createEntityManager(){

        emf = Persistence.createEntityManagerFactory("ExamplePU");

    }

    public static void closeEntityManager(){
        if (emf != null){
            emf.close();
        }
    }

}
