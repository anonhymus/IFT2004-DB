package org.anonhyme.tp3.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Anonhyme on 12/3/2016.
 */
@Entity
@Table(name = "TP2_LECTEUR", schema = "ANONHYME", catalog = "")
@Data
public class LecteurEntity {
    @Id
    @Column(name = "NOM_USAGER_LECTEUR")
    private String nomUsagerLecteur;
    @Basic
    @Column(name = "NOM_COMPLET_LEC")
    private String nomCompletLec;
    @Basic
    @Column(name = "VILLE_LEC")
    private String villeLec;
    @Basic
    @Column(name = "DATE_NAISSANCE_LEC")
    private Time dateNaissanceLec;
    @Basic
    @Column(name = "COURRIEL_LEC")
    private String courrielLec;
    @Basic
    @Column(name = "BOOL_COURRIEL_CONFIRME_LEC")
    private boolean boolCourrielConfirmeLec;

}
