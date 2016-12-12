package org.anonhyme.tp3.entity;

import javax.persistence.criteria.Order;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Timestamp;

/**
 * tp3
 * 12/12/2016.
 *
 * @author Anonhyme
 */
@StaticMetamodel(ArticleEntity.class)
public class Article_ {
    public static volatile SingularAttribute<ArticleEntity, Long> noArticle;
    public static volatile SingularAttribute<ArticleEntity, Timestamp> datePublicationArt;
    public static volatile SingularAttribute<ArticleEntity,Timestamp> dateMiseAJourArt;
    public static volatile SingularAttribute<ArticleEntity,String> exergueArt;
    public static volatile SingularAttribute<ArticleEntity,String> titreArt;
    public static volatile SingularAttribute<ArticleEntity,String> leadArt;
    public static volatile SingularAttribute<ArticleEntity,String> texteArt;
    public static volatile SingularAttribute<ArticleEntity,String> photoArt;
    public static volatile SingularAttribute<ArticleEntity,String> photoBasVignetteArt;
    public static volatile SingularAttribute<ArticleEntity,Long> nbLuArt;
    public static volatile SingularAttribute<ArticleEntity,Boolean> boolEstMajeurArt;
    public static volatile SingularAttribute<ArticleEntity,Long> longitudeArt;
    public static volatile SingularAttribute<ArticleEntity,Long> latitudeArt;
    public static volatile SingularAttribute<ArticleEntity,CategorieArticleEntity> categorieArticle;
    public static volatile SingularAttribute<ArticleEntity,JournalisteEntity> auteur;

}
