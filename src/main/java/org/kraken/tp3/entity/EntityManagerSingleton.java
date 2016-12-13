package org.kraken.tp3.entity;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/7/2016.
 */

public class EntityManagerSingleton {
    private static EntityManagerFactory entityManagerFactory = null;
    private static EntityManagerSingleton instance = null;
    private static javax.persistence.EntityManager entityManager = null;

    private EntityManagerSingleton() {
        entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static javax.persistence.EntityManager getInstance() {
        if(instance == null) {
            instance = new EntityManagerSingleton();
        }

        return entityManager;
    }
}
