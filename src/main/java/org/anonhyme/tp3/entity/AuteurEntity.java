package org.anonhyme.tp3.entity;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/8/2016.
 */
public interface AuteurEntity {

    AuteurType getType();

    void setId(long no);

    long getId();

    void setNom(String nom);

    String getNom();

    void setPrenom(String prenom);

    String getPrenom();

    void setCourriel(String courriel);

    String getCourriel();

}
