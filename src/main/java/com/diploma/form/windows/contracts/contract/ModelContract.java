package com.diploma.form.windows.contracts.contract;


import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.Contract;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelContract {
    public static void insertContract(String client, String region, String offer, String payment) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("INSERT INTO CONTRACT VALUES (1," + client + "," + region + ",'" + offer + "','" + payment + "','F')");
    }

    public static ArrayList<Contract> selectContract() {
        ArrayList<Contract> list = new ArrayList<>();
        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM CONTRACT");

            while (resultSet.next()) {
                //list.add(new Contract(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(5)));
            }
            connect.closeConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void delete(String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("DELETE FROM CONTRACT WHERE ID = '" + id + "'");
        connect.closeConnect();
    }
}
