package org.kraken.tp3.entity;

import javax.persistence.*;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_CATEGORIE_ARTICLE", schema = "ANONHYME")
public class CategorieArticleEntity {
    @Id
    @Column(name = "CODE_CATEGORIE_ARTICLE")
    private String codeCategorieArticle;

    @Basic
    @Column(name = "NOM_CATEGORIE_ART")
    private String nomCategorieArt;

    @Override
    public String toString() {
        return nomCategorieArt;
    }

    public String getCodeCategorieArticle() {
        return this.codeCategorieArticle;
    }

    public String getNomCategorieArt() {
        return this.nomCategorieArt;
    }

    public void setCodeCategorieArticle(String codeCategorieArticle) {
        this.codeCategorieArticle = codeCategorieArticle;
    }

    public void setNomCategorieArt(String nomCategorieArt) {
        this.nomCategorieArt = nomCategorieArt;
    }
}
