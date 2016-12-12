package org.anonhyme.tp3.dao;

import org.anonhyme.tp3.entity.ChroniqueurEntity;
import org.anonhyme.tp3.entity.EntityManagerSingleton;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Anonhyme on 12/7/2016.
 */

public class ChroniqueurDao {

    EntityManager entityManager = EntityManagerSingleton.getInstance();

    public List<ChroniqueurEntity> getChroniqueurs() {
//        em.getTransaction().begin();
        List<ChroniqueurEntity> chroniqueurs = (List<ChroniqueurEntity>) entityManager.createQuery("select c from ChroniqueurEntity c")
                                                                                      .getResultList();
//        em.close();
        return chroniqueurs;
    }
}
