package com.diploma.dataBase;

import com.diploma.dataBase.tables.Table;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Command {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBPersistence");
    private static EntityManager em = emf.createEntityManager();
    private static EntityTransaction tx = em.getTransaction();
    private static CriteriaBuilder builder = em.getCriteriaBuilder();

    public static EntityManagerFactory getEmf(){
        return emf;
    }

    public static EntityManager getEm(){
        return em;
    }

    public static EntityTransaction getTx(){
        return tx;
    }

    public static CriteriaBuilder getBuilder() {
        return builder;
    }

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
        CriteriaQuery criteriaQuery = builder.createQuery(table);
        Root root = criteriaQuery.from(table);
        criteriaQuery.select(root);
        Query query = em.createQuery(criteriaQuery);
        return new ArrayList(query.getResultList());
    }

    public static ArrayList select(Class table,Expression expression) {
        CriteriaQuery criteriaQuery = builder.createQuery(table);
        Root root = criteriaQuery.from(table);
        criteriaQuery.select(root).where(expression);
        Query query = em.createQuery(criteriaQuery);
        return new ArrayList(query.getResultList());
    }

    public static void delete(Class table, int id) {
        CriteriaDelete criteriaDelete = builder.createCriteriaDelete(table);
        Root root = criteriaDelete.from(table);
        criteriaDelete.where(builder.equal(root.get("id").as(Integer.class), id));

        tx.begin();
        em.createQuery(criteriaDelete).executeUpdate();
        tx.commit();
    }

}

