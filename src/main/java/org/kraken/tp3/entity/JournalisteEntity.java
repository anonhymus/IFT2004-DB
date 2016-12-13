package org.kraken.tp3.entity;

import javax.persistence.*;
import java.util.List;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_JOURNALISTE", schema = "ANONHYME")
public class JournalisteEntity implements AuteurEntity {
    @Id
    @Column(name = "NO_JOURNALISTE")
    private long id;
    @Basic
    @Column(name = "NOM_JOU")
    private String nom;
    @Basic
    @Column(name = "PRENOM_JOU")
    private String prenom;
    @Basic
    @Column(name = "PHOTO_JOU")
    private String photo;
    @Basic
    @Column(name = "COURRIEL_JOU")
    private String courriel;

    @OneToMany(mappedBy = "auteur")
    private List<ArticleEntity> articles;

    @Override
    public String toString() {
        return nom + ", " + prenom;
    }

    @Override
    public AuteurType getType() {
        return AuteurType.JOURNALISTE;
    }

    public long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getPhoto() {
        return this.photo;
    }

    public String getCourriel() {
        return this.courriel;
    }

    public List<ArticleEntity> getArticles() {
        return this.articles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setArticles(List<ArticleEntity> articles) {
        this.articles = articles;
    }
}
