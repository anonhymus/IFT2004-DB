package org.anonhyme.tp3.dao;

import org.anonhyme.tp3.entity.CategorieArticleEntity;
import org.anonhyme.tp3.entity.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.TreeMap;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/4/2016.
 */
public class CategorieArticleDao {
    EntityManager entityManager = EntityManagerSingleton.getInstance();

    public TreeMap<String, CategorieArticleEntity> getAll() {
        return null;
    }

    public void add(String code, String categorie) {
        entityManager.clear();
//        em.getTransaction().begin();
        CategorieArticleEntity categorieArticle = new CategorieArticleEntity();
        categorieArticle.setCodeCategorieArticle(code);
        categorieArticle.setNomCategorieArt(categorie);
        entityManager.persist(categorieArticle);
        entityManager.getTransaction().commit();

    }

    public List<CategorieArticleEntity> getCategories() {
        Query query = entityManager.createQuery("select c from CategorieArticleEntity c");
        return (List<CategorieArticleEntity>) query.getResultList();
    }
}
