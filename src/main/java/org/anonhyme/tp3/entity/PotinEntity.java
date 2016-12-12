package org.anonhyme.tp3.entity;

import javax.persistence.*;
import java.sql.Time;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_POTIN", schema = "ANONHYME")
public class PotinEntity {
    @Id
    @Column(name = "NO_POTIN")
    private long noPotin;
    @Basic
    @Column(name = "DATE_PUBLICATION_POT")
    private Time datePublicationPot;
    @Basic
    @Column(name = "TEXTE_POT")
    private String textePot;
    @Basic
    @Column(name = "PHOTO_POT")
    private String photoPot;
    @Basic
    @Column(name = "PHOTO_BAS_VIGNETTE_POT")
    private String photoBasVignettePot;
    @Basic
    @Column(name = "LONGITUDE_POT")
    private Long longitudePot;
    @Basic
    @Column(name = "LATITUDE_POT")
    private Long latitudePot;

}
