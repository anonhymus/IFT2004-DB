package org.anonhyme.tp3.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_ARTICLE", schema = "ANONHYME")
@Getter
@Setter
public class ArticleEntity implements ArticleBase {
    @Id
    @Column(name = "NO_ARTICLE", nullable = false)
    @SequenceGenerator(name = "id_seq", sequenceName = "ARTICLE_SEQ", allocationSize = 1)
    @GeneratedValue(generator = "id_seq", strategy = GenerationType.SEQUENCE)
    private long noArticle;

    @Basic
    @Column(name = "DATE_PUBLICATION_ART")
    @NotNull
    private Timestamp datePublicationArt;
    @Basic
    @Column(name = "DATE_MISE_A_JOUR_ART")
    private Timestamp dateMiseAJourArt;
    @Basic
    @Column(name = "EXERGUE_ART")
    private String exergueArt;
    @Basic
    @Column(name = "TITRE_ART")
    private String titreArt;
    @Basic
    @Column(name = "LEAD_ART")
    private String leadArt;
    @Basic
    @Column(name = "TEXTE_ART")
    private String texteArt;
    @Basic
    @Column(name = "PHOTO_ART")
    private String photoArt;
    @Basic
    @Column(name = "PHOTO_BAS_VIGNETTE_ART")
    private String photoBasVignetteArt;
    @Basic
    @Column(name = "NB_LU_ART")
    private Long nbLuArt;
    @Basic
    @Column(name = "BOOL_EST_MAJEUR_ART")
    private Boolean boolEstMajeurArt;
    @Basic
    @Column(name = "LONGITUDE_ART")
    private Float longitudeArt;
    @Basic
    @Column(name = "LATITUDE_ART")
    private Float latitudeArt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CODE_CATEGORIE_ARTICLE")
    private CategorieArticleEntity categorieArticle;

    @ManyToOne
    @JoinColumn(name = "NO_JOURNALISTE")
    private JournalisteEntity auteur;

    @Override
    public String toString() {
        return String.valueOf(noArticle) + ", "
               + titreArt + ", "
               + auteur.getNom() + " "
               + auteur.getPrenom();
    }

    @Override
    @Transient
    public ArticleType getType() {
        return ArticleType.ARTICLE;
    }
}
