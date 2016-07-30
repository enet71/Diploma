package com.diploma.form.windows.data.watch.table;

import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.Wave;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {

    public static ArrayList<Wave> selectRegion() throws SQLException {
        Connect connect = new Connect();
        ArrayList<Wave> arrayList = new ArrayList<>();
        ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM WAVE");
        while (resultSet.next()) {
            //arrayList.add(new Wave(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
        }
        connect.closeConnect();
        return arrayList;
    }

    public static ArrayList<String> selectRegion(String columnName) throws SQLException {
        Connect connect = new Connect();
        ArrayList<String> arrayList = new ArrayList<>();
        ResultSet resultSet = connect.getStatement().executeQuery("SELECT " + columnName + " FROM WAVE");
        while (resultSet.next()) {
            arrayList.add(resultSet.getString(1));
        }
        connect.closeConnect();
        return arrayList;
    }
}
