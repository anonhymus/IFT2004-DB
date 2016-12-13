package org.kraken.tp3.dao;

import org.kraken.tp3.application.ReponseFormulaire;
import org.kraken.tp3.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * tp3
 * org.kraken.tp3.dao.ArticleDao
 *
 * @author Anonhyme
 * @Date 12/7/2016.
 */
public class ArticleDao {
    EntityManager em = EntityManagerSingleton.getInstance();
    public static final String QUERY_TEMPLATE = "SELECT a FROM ArticleEntity a";
    public static final String WHERE_TEMPLATE = "WHERE";
    public static final String EQUAL_TEMPLATE = "a.%s=:%s";

    public static final String AND_TEMPLATE = "and ";


    public List<ArticleEntity> findAll() {
        return em.createQuery("select ae from ArticleEntity ae", ArticleEntity.class)
                 .getResultList();
    }

    public List<ArticleEntity> findArticlesByAuteur(AuteurEntity auteur) {

        return em.createQuery("SELECT j from ArticleEntity j where j.auteur=:auteur", ArticleEntity.class)
                 .setParameter("auteur", auteur)
                 .getResultList();
    }

    public ArticleEntity findArticleByTitre(String titre) {
        return (ArticleEntity) em.createQuery("SELECT j from ArticleEntity j where j.titreArt=:titre",
                                              ArticleEntity.class)
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

        return em.createQuery("SELECT j from ArticleEntity j where j.dateMiseAJourArt=:date", ArticleEntity.class)
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

    public List<ArticleEntity> findArticleByForm(ReponseFormulaire formulaire) {
        ArrayList<String> query = new ArrayList<>();
        String DYNAMIQUE_TEMPLATE = null;
        boolean isFirst = true;
        TypedQuery<ArticleEntity> articleQuery;

        query.add(String.format(QUERY_TEMPLATE));
        query.add(String.format(WHERE_TEMPLATE));

        for(FormulaireInputType fit : formulaire.getRequestParam()) {
            DYNAMIQUE_TEMPLATE = (isFirst) ? EQUAL_TEMPLATE : AND_TEMPLATE + EQUAL_TEMPLATE;
            isFirst = false;
            switch(fit) {
                case TITRE:
                    query.add(String.format(DYNAMIQUE_TEMPLATE, "titreArt", "titre"));
                    break;
                case AUTEUR:
                    query.add(String.format(DYNAMIQUE_TEMPLATE, "auteur", "auteur"));
                    break;
                case CATEGORIE:
                    query.add(String.format(DYNAMIQUE_TEMPLATE, "categorieArticle", "categorie"));
                    break;
                case TEXTE:
                    query.add(String.format(DYNAMIQUE_TEMPLATE, "texteArt", "texte"));
                    break;
                case DATE_MISE_A_JOUR:
                    query.add(String.format(DYNAMIQUE_TEMPLATE, "dateMiseAJourArt", "date"));
                    break;
                case DATE_PUBLICATION:
                    query.add(String.format(DYNAMIQUE_TEMPLATE, "datePublication", "date"));
                    break;
            }
        }
        String fullQuery = "".join(" ", query);
        TypedQuery<ArticleEntity> ae;
        articleQuery = em.createQuery(fullQuery, ArticleEntity.class);
        for(FormulaireInputType fit : formulaire.getRequestParam()) {
            switch(fit) {
                case TITRE:
                    articleQuery.setParameter("titre", formulaire.getTitre());
                    break;
                case AUTEUR:
                    articleQuery.setParameter("auteur", formulaire.getAuteur());
                    break;
                case CATEGORIE:
                    articleQuery.setParameter("categorie", formulaire.getCategorie());
                    break;
                case TEXTE:
                    articleQuery.setParameter("texte", formulaire.getTexte());
                    break;
                case DATE_MISE_A_JOUR:
                    articleQuery.setParameter("date", formulaire.getDateMiseAjour());
                    break;
                case DATE_PUBLICATION:
                    articleQuery.setParameter("date", formulaire.getDatePublication());
                    break;
            }

        }
        return articleQuery.getResultList();
    }


    public void addArticles(ArticleEntity articleEntity) {
        em.getTransaction().begin();
        articleEntity.setDatePublicationArt(new Timestamp(System.currentTimeMillis()));
        articleEntity.setDateMiseAJourArt(new Timestamp(System.currentTimeMillis()));
        em.persist(articleEntity);
        em.getTransaction().commit();
    }

    public void updateArticle(ArticleEntity articleEntity) {
        em.getTransaction().begin();
        ArticleEntity currentArticle = em.find(ArticleEntity.class, articleEntity.getNoArticle());
        currentArticle.setTitreArt(articleEntity.getTitreArt());
        currentArticle.setAuteur(articleEntity.getAuteur());
        currentArticle.setExergueArt(articleEntity.getExergueArt());
        currentArticle.setLeadArt(articleEntity.getLeadArt());
        currentArticle.setTexteArt(articleEntity.getTexteArt());
        currentArticle.setCategorieArticle(articleEntity.getCategorieArticle());
        currentArticle.setLongitudeArt(articleEntity.getLongitudeArt());
        currentArticle.setLatitudeArt(articleEntity.getLatitudeArt());
        em.getTransaction().commit();

    }

    public void purgerArticles(Timestamp timestamp) {
        StoredProcedureQuery storedProcedureQuery = em.createStoredProcedureQuery("purger");

    }
}
