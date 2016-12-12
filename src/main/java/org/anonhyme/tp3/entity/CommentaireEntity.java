package org.anonhyme.tp3.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Time;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_COMMENTAIRE", schema = "ANONHYME")
@Data
public class CommentaireEntity {
    @Id
    @Column(name = "NO_COMMENTAIRE")
    private long noCommentaire;
    @Basic
    @Column(name = "TEXTE_COM")
    private String texteCom;
    @Basic
    @Column(name = "BOOL_EST_ANONYME_COM")
    private boolean boolEstAnonymeCom;
    @Basic
    @Column(name = "BOOL_EST_APPROUVE_COM")
    private boolean boolEstApprouveCom;
    @Basic
    @Column(name = "DATE_COM")
    private Time dateCom;
}
