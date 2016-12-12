package org.anonhyme.tp3.application;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.anonhyme.tp3.entity.AuteurEntity;
import org.anonhyme.tp3.entity.CategorieArticleEntity;
import org.anonhyme.tp3.entity.FormulaireInputType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.anonhyme.tp3.entity.FormulaireInputType.*;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/8/2016.
 */
@Getter
@ToString
@EqualsAndHashCode
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
}
