package org.anonhyme.tp3.application;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.anonhyme.tp3.entity.AuteurEntity;
import org.anonhyme.tp3.entity.CategorieArticleEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by Anonhyme on 12/8/2016.
 */
@EqualsAndHashCode
@Getter
public class ReponseFormulaire {
    private AuteurEntity auteur;
    private String titre;
    private String texte;
    private CategorieArticleEntity categorie;
    private Timestamp dateMiseAjour;
    private Timestamp datePublication;
    private List<String> requestParam;

    public ReponseFormulaire() {

        requestParam = new ArrayList<>();
    }

    public void setAuteur(AuteurEntity auteur) {
        if(auteur != null) {
            requestParam.add("auteur");
            this.auteur = auteur;
        }
    }

    public void setTitre(String titre) {
        if(!titre.isEmpty()) {
            requestParam.add("titre");
            this.titre = titre;
        }
    }

    public void setTexte(String texte) {
        if(!texte.isEmpty()) {
            requestParam.add("texte");
            this.texte = texte;
        }
    }

    public void setCategorie(CategorieArticleEntity categorie) {
        if(categorie != null) {
            requestParam.add("categorie");
            this.categorie = categorie;
        }
    }

    public void setDateMiseAjour(String dateMiseAjour) {
        if(!dateMiseAjour.isEmpty()) {
            requestParam.add("dateMiseAJour");

            this.dateMiseAjour = Timestamp.valueOf(dateMiseAjour);
        }
    }

    public void setDatePublication(String datePublication) {
        if(!datePublication.isEmpty()) {
            requestParam.add("datePublication");
            this.datePublication = Timestamp.valueOf(datePublication);
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof ReponseFormulaire;
    }

    public String toString() {
        return "org.anonhyme.tp3.application.ReponseFormulaire(auteur=" + this.getAuteur() + ", titre=" + this.getTitre() + ", texte=" + this
                .getTexte() + ", categorie=" + this.getCategorie() + ", dateMiseAjour=" + this.getDateMiseAjour() + ", datePublication=" + this
                       .getDatePublication() + ", requestParam=" + this.getRequestParam() + ")";
    }
}
