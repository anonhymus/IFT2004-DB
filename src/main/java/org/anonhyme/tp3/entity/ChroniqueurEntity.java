package org.anonhyme.tp3.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Anonhyme on 12/3/2016.
 */
@Entity
@Table(name = "TP2_CHRONIQUEUR", schema = "ANONHYME")
@Getter
@Setter
public class ChroniqueurEntity implements AuteurEntity{
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
}
