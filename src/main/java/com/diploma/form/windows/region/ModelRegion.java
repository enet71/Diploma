package com.diploma.form.windows.region;

import com.diploma.dataBase.tables.Region;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;

public class ModelRegion {
    public static ArrayList<Region> selectRegion() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBPersistence");
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Region> criteriaQuery = builder.createQuery(Region.class);
        Root<Region> regionRoot = criteriaQuery.from(Region.class);
        criteriaQuery.select(regionRoot);
        Query query = em.createQuery(criteriaQuery);
        ArrayList<Region> regions = new ArrayList<>(query.getResultList());
        return regions;
    }

    public static void delete(int id) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBPersistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete<Region> criteriaDelete = builder.createCriteriaDelete(Region.class);
        Root<Region> regionRoot = criteriaDelete.from(Region.class);
        criteriaDelete.where(builder.equal(regionRoot.get("id").as(Integer.class), id));

        tx.begin();
        em.createQuery(criteriaDelete).executeUpdate();
        tx.commit();
    }
}
