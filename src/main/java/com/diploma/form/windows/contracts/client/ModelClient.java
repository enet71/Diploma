package com.diploma.form.windows.contracts.client;

import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.Client;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

public class ModelClient {

    public static void insertClient(String name, String lastName, String middleName) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("INSERT INTO CLIENT VALUES (1,'" + name + "','" + lastName + "','" + middleName + "')");
    }

    public static List<Client> selectClient() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("DBPersistence");
        EntityManager em = emf.createEntityManager();

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Client> criteriaQuery = builder.createQuery(Client.class);
        Root<Client> clientRoot = criteriaQuery.from(Client.class);
        criteriaQuery.select(clientRoot);
        Query query = em.createQuery(criteriaQuery);
        return query.getResultList();
    }

    public static void delete(String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("DELETE FROM CLIENT WHERE ID = '" + id + "'");
        connect.closeConnect();
    }
}
