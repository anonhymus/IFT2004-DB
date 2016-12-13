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
@Table(name = "TP2_LECTEUR", schema = "ANONHYME")
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

    public LecteurEntity() {
    }

    public String getNomUsagerLecteur() {
        return this.nomUsagerLecteur;
    }

    public String getNomCompletLec() {
        return this.nomCompletLec;
    }

    public String getVilleLec() {
        return this.villeLec;
    }

    public Time getDateNaissanceLec() {
        return this.dateNaissanceLec;
    }

    public String getCourrielLec() {
        return this.courrielLec;
    }

    public boolean isBoolCourrielConfirmeLec() {
        return this.boolCourrielConfirmeLec;
    }

    public void setNomUsagerLecteur(String nomUsagerLecteur) {
        this.nomUsagerLecteur = nomUsagerLecteur;
    }

    public void setNomCompletLec(String nomCompletLec) {
        this.nomCompletLec = nomCompletLec;
    }

    public void setVilleLec(String villeLec) {
        this.villeLec = villeLec;
    }

    public void setDateNaissanceLec(Time dateNaissanceLec) {
        this.dateNaissanceLec = dateNaissanceLec;
    }

    public void setCourrielLec(String courrielLec) {
        this.courrielLec = courrielLec;
    }

    public void setBoolCourrielConfirmeLec(boolean boolCourrielConfirmeLec) {
        this.boolCourrielConfirmeLec = boolCourrielConfirmeLec;
    }

    public boolean equals(Object o) {
        if(o == this) return true;
        if(!(o instanceof LecteurEntity)) return false;
        final LecteurEntity other = (LecteurEntity) o;
        if(!other.canEqual((Object) this)) return false;
        final Object this$nomUsagerLecteur = this.getNomUsagerLecteur();
        final Object other$nomUsagerLecteur = other.getNomUsagerLecteur();
        if(this$nomUsagerLecteur == null ? other$nomUsagerLecteur != null : !this$nomUsagerLecteur.equals(
                other$nomUsagerLecteur)) return false;
        final Object this$nomCompletLec = this.getNomCompletLec();
        final Object other$nomCompletLec = other.getNomCompletLec();
        if(this$nomCompletLec == null ? other$nomCompletLec != null : !this$nomCompletLec.equals(other$nomCompletLec))
            return false;
        final Object this$villeLec = this.getVilleLec();
        final Object other$villeLec = other.getVilleLec();
        if(this$villeLec == null ? other$villeLec != null : !this$villeLec.equals(other$villeLec)) return false;
        final Object this$dateNaissanceLec = this.getDateNaissanceLec();
        final Object other$dateNaissanceLec = other.getDateNaissanceLec();
        if(this$dateNaissanceLec == null ? other$dateNaissanceLec != null : !this$dateNaissanceLec.equals(
                other$dateNaissanceLec)) return false;
        final Object this$courrielLec = this.getCourrielLec();
        final Object other$courrielLec = other.getCourrielLec();
        if(this$courrielLec == null ? other$courrielLec != null : !this$courrielLec.equals(other$courrielLec))
            return false;
        if(this.isBoolCourrielConfirmeLec() != other.isBoolCourrielConfirmeLec()) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $nomUsagerLecteur = this.getNomUsagerLecteur();
        result = result * PRIME + ($nomUsagerLecteur == null ? 43 : $nomUsagerLecteur.hashCode());
        final Object $nomCompletLec = this.getNomCompletLec();
        result = result * PRIME + ($nomCompletLec == null ? 43 : $nomCompletLec.hashCode());
        final Object $villeLec = this.getVilleLec();
        result = result * PRIME + ($villeLec == null ? 43 : $villeLec.hashCode());
        final Object $dateNaissanceLec = this.getDateNaissanceLec();
        result = result * PRIME + ($dateNaissanceLec == null ? 43 : $dateNaissanceLec.hashCode());
        final Object $courrielLec = this.getCourrielLec();
        result = result * PRIME + ($courrielLec == null ? 43 : $courrielLec.hashCode());
        result = result * PRIME + (this.isBoolCourrielConfirmeLec() ? 79 : 97);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof LecteurEntity;
    }

    public String toString() {
        return "org.kraken.tp3.entity.LecteurEntity(nomUsagerLecteur=" + this.getNomUsagerLecteur() + ", nomCompletLec=" + this
                .getNomCompletLec() + ", villeLec=" + this.getVilleLec() + ", dateNaissanceLec=" + this.getDateNaissanceLec() + ", courrielLec=" + this
                       .getCourrielLec() + ", boolCourrielConfirmeLec=" + this.isBoolCourrielConfirmeLec() + ")";
    }
}
