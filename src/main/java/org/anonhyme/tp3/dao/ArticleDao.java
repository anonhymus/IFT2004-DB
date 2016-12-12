package org.anonhyme.tp3.dao;

import org.anonhyme.tp3.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.util.List;

/**
 * tp3
 * org.anonhyme.tp3.dao.ArticleDao
 *
 * @author Anonhyme
 * @Date 12/7/2016.
 */
public class ArticleDao {
    EntityManager em = EntityManagerSingleton.getInstance();
//    CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
//    CriteriaQuery<ArticleEntity> criteriaQuery = criteriaBuilder.createQuery(ArticleEntity.class);
//    Root<ArticleEntity> articleRoot = criteriaQuery.from(ArticleEntity.class);
//    Predicate condition = criteriaBuilder.gt(articleRoot.get(Article_.))

    public List<ArticleEntity> getArticles() {
        return em.createQuery("select ae from ArticleEntity ae", ArticleEntity.class)
                 .getResultList();
    }

    public void addArticles(ArticleEntity articleEntity) {
        em.getTransaction().begin();
//        articleEntity.setNoArticle(1000);
        articleEntity.setDatePublicationArt(new Timestamp(System.currentTimeMillis()));
        articleEntity.setDateMiseAJourArt(new Timestamp(System.currentTimeMillis()));
        em.persist(articleEntity);
        em.getTransaction().commit();
        em.close();
    }

    public List<ArticleEntity> findArticlesByAuteur(AuteurEntity auteur) {

        return em.createQuery("SELECT j from ArticleEntity j where j.auteur=:auteur", ArticleEntity.class)
                 .setParameter("auteur", auteur)
                 .getResultList();
    }

    public ArticleEntity findArticleByTitre(String titre) {
        return em.createQuery("SELECT j from ArticleEntity j where j.titreArt=:titre", ArticleEntity.class)
                 .setParameter("titre", titre)
                 .getSingleResult();
    }

    public List<ArticleEntity> findArticlesByDatePublication(Timestamp date) {

        return em.createQuery(
                "SELECT j from ArticleEntity j where j.datePublicationArt=:date", ArticleEntity.class)
                 .setParameter("date", date)
                 .getResultList();
    }

    public List<ArticleEntity> findArticlesByDateMiseAJour(Timestamp date) {

        return em.createQuery("SELECT j from ArticleEntity j where j.dateMiseAJourArt=:date",
                              ArticleEntity.class)
                 .setParameter("date", date).getResultList();
    }

    public List<ArticleEntity> findArticleByCategorie(CategorieArticleEntity categorie) {

        return em.createQuery(
                "SELECT j from ArticleEntity j where j.categorieArticle=:categorie", ArticleEntity.class)
                 .setParameter("categorie", categorie)
                 .getResultList();
    }

    public List<ArticleEntity> findArticleByTexte(String texte) {
        return em.createQuery("SELECT j from ArticleEntity j where j.texteArt=:texte", ArticleEntity.class)
                 .setParameter("texte", texte)
                 .getResultList();
    }
}
