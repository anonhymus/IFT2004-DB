package org.anonhyme.tp3.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_CHRONIQUE", schema = "ANONHYME")
@Getter
@Setter
@EqualsAndHashCode
public class ChroniqueEntity implements ArticleBase {
    @Id
    @Column(name = "NO_CHRONIQUE")
    private long noChronique;
    @Basic
    @Column(name = "DATE_PUBLICATION_CHR")
    private Timestamp datePublicationChr;
    @Basic
    @Column(name = "DATE_MISE_A_JOUR_CHR")
    private Timestamp dateMiseAJourChr;
    @Basic
    @Column(name = "EXERGUE_CHR")
    private String exergueChr;
    @Basic
    @Column(name = "TITRE_CHR")
    private String titreChr;
    @Basic
    @Column(name = "LEAD_CHR")
    private String leadChr;
    @Basic
    @Column(name = "TEXTE_CHR")
    private String texteChr;
    @Basic
    @Column(name = "PHOTO_CHR")
    private String photoChr;
    @Basic
    @Column(name = "PHOTO_BAS_VIGNETTE_CHR")
    private String photoBasVignetteChr;
    @Basic
    @Column(name = "LONGITUDE_CHR")
    private Long longitudeChr;
    @Basic
    @Column(name = "LATITUDE_CHR")
    private Long latitudeChr;

    @ManyToOne
    @JoinColumn(name = "NO_CHRONIQUEUR")
    private ChroniqueurEntity chroniqueur;

    @OneToOne
    @JoinColumn(name = "NO_SOURCE_PHOTO")
    private SourcePhotoEntity sourcePhoto;

    @Override
    public String toString() {
        return String.valueOf(noChronique) + ", "
               + titreChr + ", "
               + chroniqueur.getNom() + " "
               + chroniqueur.getPrenom();
    }

    @Override
    @Transient
    public ArticleType getType() {
        return ArticleType.CHRONIQUE;
    }


}
