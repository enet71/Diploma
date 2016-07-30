package com.diploma.dataBase;

import com.diploma.dataBase.tables.Table;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.ArrayList;

public class Command {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBPersistence");
    private static EntityManager em = emf.createEntityManager();
    private static EntityTransaction tx = em.getTransaction();

    public static void insert(String sql) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate(sql);
        connect.closeConnect();
    }

    public static void insert(Table table) {
        tx.begin();
        em.persist(table);
        tx.commit();
        /*em.close();
        emf.close();*/
    }

    public static ArrayList select(Class table) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = builder.createQuery(table);
        Root root = criteriaQuery.from(table);
        criteriaQuery.select(root);
        Query query = em.createQuery(criteriaQuery);
        return new ArrayList(query.getResultList());
    }

    public static void delete(Class table, int id) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaDelete criteriaDelete = builder.createCriteriaDelete(table);
        Root root = criteriaDelete.from(table);
        criteriaDelete.where(builder.equal(root.get("id").as(Integer.class), id));

        tx.begin();
        em.createQuery(criteriaDelete).executeUpdate();
        tx.commit();
    }

}

