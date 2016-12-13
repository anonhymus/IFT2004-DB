package org.kraken.tp3.entity;

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

    public CommentaireEntity() {
    }

    public long getNoCommentaire() {
        return this.noCommentaire;
    }

    public String getTexteCom() {
        return this.texteCom;
    }

    public boolean isBoolEstAnonymeCom() {
        return this.boolEstAnonymeCom;
    }

    public boolean isBoolEstApprouveCom() {
        return this.boolEstApprouveCom;
    }

    public Time getDateCom() {
        return this.dateCom;
    }

    public void setNoCommentaire(long noCommentaire) {
        this.noCommentaire = noCommentaire;
    }

    public void setTexteCom(String texteCom) {
        this.texteCom = texteCom;
    }

    public void setBoolEstAnonymeCom(boolean boolEstAnonymeCom) {
        this.boolEstAnonymeCom = boolEstAnonymeCom;
    }

    public void setBoolEstApprouveCom(boolean boolEstApprouveCom) {
        this.boolEstApprouveCom = boolEstApprouveCom;
    }

    public void setDateCom(Time dateCom) {
        this.dateCom = dateCom;
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof CommentaireEntity)) return false;
        final CommentaireEntity other = (CommentaireEntity) o;
        if(!other.canEqual((Object) this)) return false;
        if(this.getNoCommentaire() != other.getNoCommentaire()) return false;
        final Object this$texteCom = this.getTexteCom();
        final Object other$texteCom = other.getTexteCom();
        if(this$texteCom == null ? other$texteCom != null : !this$texteCom.equals(other$texteCom)) return false;
        if(this.isBoolEstAnonymeCom() != other.isBoolEstAnonymeCom()) return false;
        if(this.isBoolEstApprouveCom() != other.isBoolEstApprouveCom()) return false;
        final Object this$dateCom = this.getDateCom();
        final Object other$dateCom = other.getDateCom();
        if(this$dateCom == null ? other$dateCom != null : !this$dateCom.equals(other$dateCom)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $noCommentaire = this.getNoCommentaire();
        result = result * PRIME + (int) ($noCommentaire >>> 32 ^ $noCommentaire);
        final Object $texteCom = this.getTexteCom();
        result = result * PRIME + ($texteCom == null ? 43 : $texteCom.hashCode());
        result = result * PRIME + (this.isBoolEstAnonymeCom() ? 79 : 97);
        result = result * PRIME + (this.isBoolEstApprouveCom() ? 79 : 97);
        final Object $dateCom = this.getDateCom();
        result = result * PRIME + ($dateCom == null ? 43 : $dateCom.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof CommentaireEntity;
    }

    public String toString() {
        return "org.kraken.tp3.entity.CommentaireEntity(noCommentaire=" + this.getNoCommentaire() + ", texteCom=" + this
                .getTexteCom() + ", boolEstAnonymeCom=" + this.isBoolEstAnonymeCom() + ", boolEstApprouveCom=" + this.isBoolEstApprouveCom() + ", dateCom=" + this
                       .getDateCom() + ")";
    }
}
