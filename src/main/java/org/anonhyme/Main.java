package org.anonhyme;

import org.anonhyme.tp3.entity.CategorieArticleEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * tp3

 * @autor Anonhyme
 * @Date 12/3/2016.
 */

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("NewPersistenceUnit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        CategorieArticleEntity categorieArticle = new CategorieArticleEntity();
//        categorieArticle.setCodeCategorieArticle("FOO");
//        categorieArticle.setNomCategorieArt("Foo Bar");

        categorieArticle = entityManager.find(CategorieArticleEntity.class, "FOO");
        System.out.println(categorieArticle.getNomCategorieArt());
//        entityManager.persist(categorieArticle);
        entityManager.getTransaction().commit();
//        entityManager.close();
        entityManagerFactory.close();

    }
}
