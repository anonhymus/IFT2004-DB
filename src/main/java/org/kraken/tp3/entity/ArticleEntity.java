package org.kraken.tp3.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@NamedStoredProcedureQueries(
        @NamedStoredProcedureQuery(
                name = "purger",
                procedureName = "SP_PURGER_TEXTES",
                parameters = {
                        @StoredProcedureParameter(mode = ParameterMode.IN, type = Timestamp.class, name = "date")
                }
        )
)
@Entity
@Table(name = "TP2_ARTICLE", schema = "ANONHYME")
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

    public long getNoArticle() {
        return this.noArticle;
    }

    public Timestamp getDatePublicationArt() {
        return this.datePublicationArt;
    }

    public Timestamp getDateMiseAJourArt() {
        return this.dateMiseAJourArt;
    }

    public String getExergueArt() {
        return this.exergueArt;
    }

    public String getTitreArt() {
        return this.titreArt;
    }

    public String getLeadArt() {
        return this.leadArt;
    }

    public String getTexteArt() {
        return this.texteArt;
    }

    public String getPhotoArt() {
        return this.photoArt;
    }

    public String getPhotoBasVignetteArt() {
        return this.photoBasVignetteArt;
    }

    public Long getNbLuArt() {
        return this.nbLuArt;
    }

    public Boolean getBoolEstMajeurArt() {
        return this.boolEstMajeurArt;
    }

    public Float getLongitudeArt() {
        return this.longitudeArt;
    }

    public Float getLatitudeArt() {
        return this.latitudeArt;
    }

    public CategorieArticleEntity getCategorieArticle() {
        return this.categorieArticle;
    }

    public JournalisteEntity getAuteur() {
        return this.auteur;
    }

    public void setNoArticle(long noArticle) {
        this.noArticle = noArticle;
    }

    public void setDatePublicationArt(Timestamp datePublicationArt) {
        this.datePublicationArt = datePublicationArt;
    }

    public void setDateMiseAJourArt(Timestamp dateMiseAJourArt) {
        this.dateMiseAJourArt = dateMiseAJourArt;
    }

    public void setExergueArt(String exergueArt) {
        this.exergueArt = exergueArt;
    }

    public void setTitreArt(String titreArt) {
        this.titreArt = titreArt;
    }

    public void setLeadArt(String leadArt) {
        this.leadArt = leadArt;
    }

    public void setTexteArt(String texteArt) {
        this.texteArt = texteArt;
    }

    public void setPhotoArt(String photoArt) {
        this.photoArt = photoArt;
    }

    public void setPhotoBasVignetteArt(String photoBasVignetteArt) {
        this.photoBasVignetteArt = photoBasVignetteArt;
    }

    public void setNbLuArt(Long nbLuArt) {
        this.nbLuArt = nbLuArt;
    }

    public void setBoolEstMajeurArt(Boolean boolEstMajeurArt) {
        this.boolEstMajeurArt = boolEstMajeurArt;
    }

    public void setLongitudeArt(Float longitudeArt) {
        this.longitudeArt = longitudeArt;
    }

    public void setLatitudeArt(Float latitudeArt) {
        this.latitudeArt = latitudeArt;
    }

    public void setCategorieArticle(CategorieArticleEntity categorieArticle) {
        this.categorieArticle = categorieArticle;
    }

    public void setAuteur(JournalisteEntity auteur) {
        this.auteur = auteur;
    }
}
