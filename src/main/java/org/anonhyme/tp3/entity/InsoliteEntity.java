package org.anonhyme.tp3.entity;

import javax.persistence.*;
import java.sql.Time;

/**
 * Created by Anonhyme on 12/3/2016.
 */
@Entity
@Table(name = "TP2_INSOLITE", schema = "ANONHYME", catalog = "")
public class InsoliteEntity {
    @Id
    @Column(name = "DATE_PUBLICATION_INS")
    private Time datePublicationIns;
    @Basic
    @Column(name = "TITRE_INS")
    private String titreIns;
    @Basic
    @Column(name = "TEXTE_INS")
    private String texteIns;
    @Basic
    @Column(name = "PHOTO_INS")
    private String photoIns;
    @Basic
    @Column(name = "PHOTO_BAS_VIGNETTE_INS")
    private String photoBasVignetteIns;
    @Basic
    @Column(name = "LONGITUDE_INS")
    private Long longitudeIns;
    @Basic
    @Column(name = "LATITUDE_INS")
    private Long latitudeIns;
}
