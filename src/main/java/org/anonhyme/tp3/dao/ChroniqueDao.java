package org.anonhyme.tp3.dao;

import org.anonhyme.tp3.entity.AuteurEntity;
import org.anonhyme.tp3.entity.ChroniqueEntity;
import org.anonhyme.tp3.entity.EntityManagerSingleton;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/7/2016.
 */
public class ChroniqueDao {
    EntityManager em = EntityManagerSingleton.getInstance();

    public List<ChroniqueEntity> findArticleByAuteur(AuteurEntity auteur) {
        return em.createQuery(
                "SELECT c from ChroniqueEntity c where c.chroniqueur=:auteur", ChroniqueEntity.class)
                 .setParameter("auteur", auteur)
                 .getResultList();
    }
}
