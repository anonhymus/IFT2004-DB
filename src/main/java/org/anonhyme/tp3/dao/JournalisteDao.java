package org.anonhyme.tp3.dao;

import org.anonhyme.tp3.entity.ChroniqueurEntity;
import org.anonhyme.tp3.entity.EntityManagerSingleton;
import org.anonhyme.tp3.entity.JournalisteEntity;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Anonhyme on 12/7/2016.
 */
public class JournalisteDao {
    EntityManager entityManager;

    public JournalisteDao() {
        this.entityManager = EntityManagerSingleton.getInstance();
    }

    public List<JournalisteEntity> getJournalistes() {
        List<JournalisteEntity> journalistes = (List<JournalisteEntity>) entityManager.createQuery("select j from JournalisteEntity j")
                                                                                      .getResultList();
        return journalistes;
    }

    public JournalisteEntity getJournalistesByNom(String nom) {
        return entityManager.createQuery("select java from JournalisteEntity j where j.nom =:nom", JournalisteEntity.class)
                            .setParameter("nom", nom).getSingleResult();
    }
}
