package org.kraken.tp3.application;

import org.kraken.tp3.entity.AuteurEntity;
import org.kraken.tp3.entity.CategorieArticleEntity;
import org.kraken.tp3.entity.FormulaireInputType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.kraken.tp3.entity.FormulaireInputType.*;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/8/2016.
 */
public class ReponseFormulaire {
    private AuteurEntity auteur;
    private String titre;
    private String texte;
    private CategorieArticleEntity categorie;
    private Timestamp dateMiseAjour;
    private Timestamp datePublication;
    private List<FormulaireInputType> requestParam;
    private int requestParamCount = 0;

    public ReponseFormulaire() {
        requestParam = new ArrayList<>();
    }

    public void setAuteur(AuteurEntity auteur) {
        if(auteur != null) {
            requestParam.add(AUTEUR);
            this.auteur = auteur;
            this.requestParamCount++;
        }
    }

    public void setTitre(String titre) {
        if(!titre.isEmpty()) {
            requestParam.add(TITRE);
            this.titre = titre;
            this.requestParamCount++;
        }
    }

    public void setTexte(String texte) {
        if(!texte.isEmpty()) {
            requestParam.add(TEXTE);
            this.texte = texte;
            this.requestParamCount++;
        }
    }

    public void setCategorie(CategorieArticleEntity categorie) {
        if(categorie != null) {
            requestParam.add(CATEGORIE);
            this.categorie = categorie;
            this.requestParamCount++;
        }
    }

    public void setDateMiseAjour(String dateMiseAjour) {
        if(!dateMiseAjour.isEmpty()) {
            requestParam.add(DATE_MISE_A_JOUR);
            this.dateMiseAjour = Timestamp.valueOf(dateMiseAjour);
            this.requestParamCount++;
        }
    }

    public void setDatePublication(String datePublication) {
        if(!datePublication.isEmpty()) {
            requestParam.add(DATE_PUBLICATION);
            this.datePublication = Timestamp.valueOf(datePublication);
            this.requestParamCount++;
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ReponseFormulaire;
    }

    public AuteurEntity getAuteur() {
        return this.auteur;
    }

    public String getTitre() {
        return this.titre;
    }

    public String getTexte() {
        return this.texte;
    }

    public CategorieArticleEntity getCategorie() {
        return this.categorie;
    }

    public Timestamp getDateMiseAjour() {
        return this.dateMiseAjour;
    }

    public Timestamp getDatePublication() {
        return this.datePublication;
    }

    public List<FormulaireInputType> getRequestParam() {
        return this.requestParam;
    }

    public int getRequestParamCount() {
        return this.requestParamCount;
    }

//    public boolean equals(Object o) {
//        if(o == this) return true;
//        if(!(o instanceof ReponseFormulaire)) return false;
//        final ReponseFormulaire other = (ReponseFormulaire) o;
//        if(!other.canEqual((Object) this)) return false;
//        final Object this$auteur = this.getAuteur();
//        final Object other$auteur = other.getAuteur();
//        if(this$auteur == null ? other$auteur != null : !this$auteur.equals(other$auteur)) return false;
//        final Object this$titre = this.getTitre();
//        final Object other$titre = other.getTitre();
//        if(this$titre == null ? other$titre != null : !this$titre.equals(other$titre)) return false;
//        final Object this$texte = this.getTexte();
//        final Object other$texte = other.getTexte();
//        if(this$texte == null ? other$texte != null : !this$texte.equals(other$texte)) return false;
//        final Object this$categorie = this.getCategorie();
//        final Object other$categorie = other.getCategorie();
//        if(this$categorie == null ? other$categorie != null : !this$categorie.equals(other$categorie)) return false;
//        final Object this$dateMiseAjour = this.getDateMiseAjour();
//        final Object other$dateMiseAjour = other.getDateMiseAjour();
//        if(this$dateMiseAjour == null ? other$dateMiseAjour != null : !this$dateMiseAjour.equals(other$dateMiseAjour))
//            return false;
//        final Object this$datePublication = this.getDatePublication();
//        final Object other$datePublication = other.getDatePublication();
//        if(this$datePublication == null ? other$datePublication != null : !this$datePublication.equals(
//                other$datePublication)) return false;
//        final Object this$requestParam = this.getRequestParam();
//        final Object other$requestParam = other.getRequestParam();
//        if(this$requestParam == null ? other$requestParam != null : !this$requestParam.equals(other$requestParam))
//            return false;
//        if(this.getRequestParamCount() != other.getRequestParamCount()) return false;
//        return true;
//    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $auteur = this.getAuteur();
        result = result * PRIME + ($auteur == null ? 43 : $auteur.hashCode());
        final Object $titre = this.getTitre();
        result = result * PRIME + ($titre == null ? 43 : $titre.hashCode());
        final Object $texte = this.getTexte();
        result = result * PRIME + ($texte == null ? 43 : $texte.hashCode());
        final Object $categorie = this.getCategorie();
        result = result * PRIME + ($categorie == null ? 43 : $categorie.hashCode());
        final Object $dateMiseAjour = this.getDateMiseAjour();
        result = result * PRIME + ($dateMiseAjour == null ? 43 : $dateMiseAjour.hashCode());
        final Object $datePublication = this.getDatePublication();
        result = result * PRIME + ($datePublication == null ? 43 : $datePublication.hashCode());
        final Object $requestParam = this.getRequestParam();
        result = result * PRIME + ($requestParam == null ? 43 : $requestParam.hashCode());
        result = result * PRIME + this.getRequestParamCount();
        return result;
    }

    public String toString() {
        return "org.kraken.tp3.application.ReponseFormulaire(auteur=" + this.getAuteur() + ", titre=" + this.getTitre() + ", texte=" + this
                .getTexte() + ", categorie=" + this.getCategorie() + ", dateMiseAjour=" + this.getDateMiseAjour() + ", datePublication=" + this
                       .getDatePublication() + ", requestParam=" + this.getRequestParam() + ", requestParamCount=" + this
                       .getRequestParamCount() + ")";
    }
}
