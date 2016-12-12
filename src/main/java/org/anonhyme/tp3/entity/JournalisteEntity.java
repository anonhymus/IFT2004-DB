package org.anonhyme.tp3.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anonhyme on 12/3/2016.
 */
@Entity
@Table(name = "TP2_JOURNALISTE", schema = "ANONHYME")
@Getter
@Setter
public class JournalisteEntity implements AuteurEntity{
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
}
