package org.anonhyme.tp3.entity;

import javax.persistence.*;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/3/2016.
 */
@Entity
@Table(name = "TP2_SOURCE_PHOTO", schema = "ANONHYME")
public class SourcePhotoEntity {
    @Id
    @Column(name = "NO_SOURCE_PHOTO")
    private long noSourcePhoto;
    @Basic
    @Column(name = "NOM_COMPLET_SOU")
    private String nomCompletSou;

    public SourcePhotoEntity() {
    }

    public long getNoSourcePhoto() {
        return this.noSourcePhoto;
    }

    public String getNomCompletSou() {
        return this.nomCompletSou;
    }

    public void setNoSourcePhoto(long noSourcePhoto) {
        this.noSourcePhoto = noSourcePhoto;
    }

    public void setNomCompletSou(String nomCompletSou) {
        this.nomCompletSou = nomCompletSou;
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof SourcePhotoEntity)) return false;
        final SourcePhotoEntity other = (SourcePhotoEntity) o;
        if(!other.canEqual((Object) this)) return false;
        if(this.getNoSourcePhoto() != other.getNoSourcePhoto()) return false;
        final Object this$nomCompletSou = this.getNomCompletSou();
        final Object other$nomCompletSou = other.getNomCompletSou();
        if(this$nomCompletSou == null ? other$nomCompletSou != null : !this$nomCompletSou.equals(other$nomCompletSou))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $noSourcePhoto = this.getNoSourcePhoto();
        result = result * PRIME + (int) ($noSourcePhoto >>> 32 ^ $noSourcePhoto);
        final Object $nomCompletSou = this.getNomCompletSou();
        result = result * PRIME + ($nomCompletSou == null ? 43 : $nomCompletSou.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof SourcePhotoEntity;
    }

    public String toString() {
        return "org.anonhyme.tp3.entity.SourcePhotoEntity(noSourcePhoto=" + this.getNoSourcePhoto() + ", nomCompletSou=" + this
                .getNomCompletSou() + ")";
    }
}
