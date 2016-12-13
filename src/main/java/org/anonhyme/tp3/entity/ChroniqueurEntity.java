package org.anonhyme.tp3.entity;

import javax.persistence.*;
import java.util.List;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_CHRONIQUEUR", schema = "ANONHYME")
public class ChroniqueurEntity implements AuteurEntity {
    @Id
    @Column(name = "NO_CHRONIQUEUR")
    private long id;
    @Basic
    @Column(name = "NOM_CHO")
    private String nom;
    @Basic
    @Column(name = "PRENOM_CHO")
    private String prenom;
    @Basic
    @Column(name = "ENTETE_CHO")
    private String entete;
    @Basic
    @Column(name = "COURRIEL_CHO")
    private String courriel;

    @OneToMany(mappedBy = "chroniqueur")
    private List<ChroniqueEntity> chroniques;


    @Override
    public AuteurType getType() {
        return AuteurType.CHRONIQUEUR;
    }


    @Override
    public String toString() {
        return nom + ", " + prenom;
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

    public String getEntete() {
        return this.entete;
    }

    public String getCourriel() {
        return this.courriel;
    }

    public List<ChroniqueEntity> getChroniques() {
        return this.chroniques;
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

    public void setEntete(String entete) {
        this.entete = entete;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public void setChroniques(List<ChroniqueEntity> chroniques) {
        this.chroniques = chroniques;
    }
}
