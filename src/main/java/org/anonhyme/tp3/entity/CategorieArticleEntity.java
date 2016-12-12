package org.anonhyme.tp3.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_CATEGORIE_ARTICLE", schema = "ANONHYME")
@Getter
@Setter
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
}
