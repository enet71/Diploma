package com.diploma.form.windows.data.add.seismic;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.Seismic;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelSeismic {
    public static void insertSeismic(String region, String name, String phone, String mail) throws SQLException {
        Command.insert("INSERT INTO SEISMIC VALUES (NULL,'" + region + "','" + name + "','" + phone + "','" + mail + "')");
    }

    public static ArrayList<Seismic> selectSeismic() throws SQLException {
        Connect connect = new Connect();
        ArrayList<Seismic> arrayList = new ArrayList<>();
        ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM Seismic");
        while (resultSet.next()) {
            //arrayList.add(new Seismic(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
        }
        connect.closeConnect();
        return arrayList;
    }

    public static void delete(String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("DELETE FROM SEISMIC WHERE ID = '" + id + "'");
        connect.closeConnect();
    }
}
