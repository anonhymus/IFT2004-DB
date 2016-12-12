package org.anonhyme.tp3.dao;

import org.anonhyme.tp3.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Anonhyme on 12/7/2016.
 */
public class ArticleDao {
    EntityManager em = EntityManagerSingleton.getInstance();

//    @PersistenceContext(unitName = "NewPersistenceUnit")
//    private EntityManager em = entityManagerFactory;


    public List<ArticleEntity> getArticles() {
//        em.getTransaction().begin();
        return (List<ArticleEntity>) em.createQuery("select ae from ArticleEntity ae", ArticleEntity.class)
                                       .getResultList();
    }

    public void addArticles(ArticleEntity articleEntity) {

    }

    public ArticleEntity getArticleById(long noArticle) {
        return em.find(ArticleEntity.class, noArticle);
    }

    //Todo fetch chronique
    public List<ArticleEntity> getArticlesByAuteur(AuteurEntity auteurEntity) {
        TypedQuery<JournalisteEntity> query;
        if (auteurEntity.getType() == AuteurType.JOURNALISTE) {
            query = em.createQuery("SELECT j from JournalisteEntity j where j.id=:noJournaliste", JournalisteEntity.class)
                      .setParameter("noJournaliste", auteurEntity.getId());
            return query.getSingleResult().getArticles();
        } else {
            return null;
        }
    }


    public List<ArticleEntity> getArticlesByAuteurNom(String auteur) {
        TypedQuery<JournalisteEntity> query = em.createQuery("SELECT j from JournalisteEntity j where j.nom=:nomJournaliste", JournalisteEntity.class)
                                                .setParameter("nomJournaliste", auteur);
        return query.getSingleResult().getArticles();
    }

    public ArticleEntity getArticleByTitre(String titre) {
        TypedQuery<ArticleEntity> query = em.createQuery("SELECT j from ArticleEntity j where j.titreArt=:titre", ArticleEntity.class)
                                            .setParameter("titre", titre);

        return query.getSingleResult();
    }

    public List<ArticleEntity> getArticlesByDatePublication(Timestamp date) {
        TypedQuery<ArticleEntity> query = em.createQuery("SELECT j from ArticleEntity j where j.datePublicationArt=:date", ArticleEntity.class)
                                            .setParameter("date", date);
        return query.getResultList();
    }

    public List<ArticleEntity> getArticlesByDateMiseAJour(Timestamp date) {
        TypedQuery<ArticleEntity> query = em.createQuery("SELECT j from ArticleEntity j where j.dateMiseAJourArt=:date", ArticleEntity.class)
                                            .setParameter("date", date);
        return query.getResultList();
    }

    public List<ArticleEntity> getArticleByAuteur(AuteurEntity auteur) {
        TypedQuery<ArticleEntity> query = em.createQuery("SELECT j from ArticleEntity j where j.auteur=:auteur", ArticleEntity.class)
                                            .setParameter("auteur", auteur);
        return query.getResultList();
    }

    public List<ArticleEntity> getArticleByCategorie(CategorieArticleEntity categorie) {
        TypedQuery<ArticleEntity> query = em.createQuery("SELECT j from ArticleEntity j where j.categorieArticle=:categorie", ArticleEntity.class)
                                            .setParameter("categorie", categorie);
        return query.getResultList();
    }
}
