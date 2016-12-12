package org.anonhyme.tp3.dao;

import org.anonhyme.tp3.entity.EntityManagerSingleton;
import org.anonhyme.tp3.entity.JournalisteEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * tp3
 *
 * @autor Anonhyme
 * @Date 12/7/2016.
 */
public class JournalisteDao {
    EntityManager entityManager;

    public JournalisteDao() {
        this.entityManager = EntityManagerSingleton.getInstance();
    }

    public List<JournalisteEntity> getJournalistes() {
        return (List<JournalisteEntity>) entityManager.createQuery("select j from JournalisteEntity j")
                                                      .getResultList();
    }

    public JournalisteEntity getJournalistesByNom(String nom) {
        return entityManager.createQuery("select java from JournalisteEntity j where j.nom =:nom",
                                         JournalisteEntity.class)
                            .setParameter("nom", nom).getSingleResult();
    }
}
