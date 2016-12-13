package org.kraken.tp3.entity;

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


    public long getNoChronique() {
        return this.noChronique;
    }

    public Timestamp getDatePublicationChr() {
        return this.datePublicationChr;
    }

    public Timestamp getDateMiseAJourChr() {
        return this.dateMiseAJourChr;
    }

    public String getExergueChr() {
        return this.exergueChr;
    }

    public String getTitreChr() {
        return this.titreChr;
    }

    public String getLeadChr() {
        return this.leadChr;
    }

    public String getTexteChr() {
        return this.texteChr;
    }

    public String getPhotoChr() {
        return this.photoChr;
    }

    public String getPhotoBasVignetteChr() {
        return this.photoBasVignetteChr;
    }

    public Long getLongitudeChr() {
        return this.longitudeChr;
    }

    public Long getLatitudeChr() {
        return this.latitudeChr;
    }

    public ChroniqueurEntity getChroniqueur() {
        return this.chroniqueur;
    }

    public SourcePhotoEntity getSourcePhoto() {
        return this.sourcePhoto;
    }

    public void setNoChronique(long noChronique) {
        this.noChronique = noChronique;
    }

    public void setDatePublicationChr(Timestamp datePublicationChr) {
        this.datePublicationChr = datePublicationChr;
    }

    public void setDateMiseAJourChr(Timestamp dateMiseAJourChr) {
        this.dateMiseAJourChr = dateMiseAJourChr;
    }

    public void setExergueChr(String exergueChr) {
        this.exergueChr = exergueChr;
    }

    public void setTitreChr(String titreChr) {
        this.titreChr = titreChr;
    }

    public void setLeadChr(String leadChr) {
        this.leadChr = leadChr;
    }

    public void setTexteChr(String texteChr) {
        this.texteChr = texteChr;
    }

    public void setPhotoChr(String photoChr) {
        this.photoChr = photoChr;
    }

    public void setPhotoBasVignetteChr(String photoBasVignetteChr) {
        this.photoBasVignetteChr = photoBasVignetteChr;
    }

    public void setLongitudeChr(Long longitudeChr) {
        this.longitudeChr = longitudeChr;
    }

    public void setLatitudeChr(Long latitudeChr) {
        this.latitudeChr = latitudeChr;
    }

    public void setChroniqueur(ChroniqueurEntity chroniqueur) {
        this.chroniqueur = chroniqueur;
    }

    public void setSourcePhoto(SourcePhotoEntity sourcePhoto) {
        this.sourcePhoto = sourcePhoto;
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof ChroniqueEntity)) return false;
        final ChroniqueEntity other = (ChroniqueEntity) o;
        if(!other.canEqual((Object) this)) return false;
        if(this.getNoChronique() != other.getNoChronique()) return false;
        final Object this$datePublicationChr = this.getDatePublicationChr();
        final Object other$datePublicationChr = other.getDatePublicationChr();
        if(this$datePublicationChr == null ? other$datePublicationChr != null : !this$datePublicationChr.equals(
                other$datePublicationChr)) return false;
        final Object this$dateMiseAJourChr = this.getDateMiseAJourChr();
        final Object other$dateMiseAJourChr = other.getDateMiseAJourChr();
        if(this$dateMiseAJourChr == null ? other$dateMiseAJourChr != null : !this$dateMiseAJourChr.equals(
                other$dateMiseAJourChr)) return false;
        final Object this$exergueChr = this.getExergueChr();
        final Object other$exergueChr = other.getExergueChr();
        if(this$exergueChr == null ? other$exergueChr != null : !this$exergueChr.equals(other$exergueChr)) return false;
        final Object this$titreChr = this.getTitreChr();
        final Object other$titreChr = other.getTitreChr();
        if(this$titreChr == null ? other$titreChr != null : !this$titreChr.equals(other$titreChr)) return false;
        final Object this$leadChr = this.getLeadChr();
        final Object other$leadChr = other.getLeadChr();
        if(this$leadChr == null ? other$leadChr != null : !this$leadChr.equals(other$leadChr)) return false;
        final Object this$texteChr = this.getTexteChr();
        final Object other$texteChr = other.getTexteChr();
        if(this$texteChr == null ? other$texteChr != null : !this$texteChr.equals(other$texteChr)) return false;
        final Object this$photoChr = this.getPhotoChr();
        final Object other$photoChr = other.getPhotoChr();
        if(this$photoChr == null ? other$photoChr != null : !this$photoChr.equals(other$photoChr)) return false;
        final Object this$photoBasVignetteChr = this.getPhotoBasVignetteChr();
        final Object other$photoBasVignetteChr = other.getPhotoBasVignetteChr();
        if(this$photoBasVignetteChr == null ? other$photoBasVignetteChr != null : !this$photoBasVignetteChr.equals(
                other$photoBasVignetteChr)) return false;
        final Object this$longitudeChr = this.getLongitudeChr();
        final Object other$longitudeChr = other.getLongitudeChr();
        if(this$longitudeChr == null ? other$longitudeChr != null : !this$longitudeChr.equals(other$longitudeChr))
            return false;
        final Object this$latitudeChr = this.getLatitudeChr();
        final Object other$latitudeChr = other.getLatitudeChr();
        if(this$latitudeChr == null ? other$latitudeChr != null : !this$latitudeChr.equals(other$latitudeChr))
            return false;
        final Object this$chroniqueur = this.getChroniqueur();
        final Object other$chroniqueur = other.getChroniqueur();
        if(this$chroniqueur == null ? other$chroniqueur != null : !this$chroniqueur.equals(other$chroniqueur))
            return false;
        final Object this$sourcePhoto = this.getSourcePhoto();
        final Object other$sourcePhoto = other.getSourcePhoto();
        if(this$sourcePhoto == null ? other$sourcePhoto != null : !this$sourcePhoto.equals(other$sourcePhoto))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $noChronique = this.getNoChronique();
        result = result * PRIME + (int) ($noChronique >>> 32 ^ $noChronique);
        final Object $datePublicationChr = this.getDatePublicationChr();
        result = result * PRIME + ($datePublicationChr == null ? 43 : $datePublicationChr.hashCode());
        final Object $dateMiseAJourChr = this.getDateMiseAJourChr();
        result = result * PRIME + ($dateMiseAJourChr == null ? 43 : $dateMiseAJourChr.hashCode());
        final Object $exergueChr = this.getExergueChr();
        result = result * PRIME + ($exergueChr == null ? 43 : $exergueChr.hashCode());
        final Object $titreChr = this.getTitreChr();
        result = result * PRIME + ($titreChr == null ? 43 : $titreChr.hashCode());
        final Object $leadChr = this.getLeadChr();
        result = result * PRIME + ($leadChr == null ? 43 : $leadChr.hashCode());
        final Object $texteChr = this.getTexteChr();
        result = result * PRIME + ($texteChr == null ? 43 : $texteChr.hashCode());
        final Object $photoChr = this.getPhotoChr();
        result = result * PRIME + ($photoChr == null ? 43 : $photoChr.hashCode());
        final Object $photoBasVignetteChr = this.getPhotoBasVignetteChr();
        result = result * PRIME + ($photoBasVignetteChr == null ? 43 : $photoBasVignetteChr.hashCode());
        final Object $longitudeChr = this.getLongitudeChr();
        result = result * PRIME + ($longitudeChr == null ? 43 : $longitudeChr.hashCode());
        final Object $latitudeChr = this.getLatitudeChr();
        result = result * PRIME + ($latitudeChr == null ? 43 : $latitudeChr.hashCode());
        final Object $chroniqueur = this.getChroniqueur();
        result = result * PRIME + ($chroniqueur == null ? 43 : $chroniqueur.hashCode());
        final Object $sourcePhoto = this.getSourcePhoto();
        result = result * PRIME + ($sourcePhoto == null ? 43 : $sourcePhoto.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof ChroniqueEntity;
    }
}
