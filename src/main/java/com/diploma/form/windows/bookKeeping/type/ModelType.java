package com.diploma.form.windows.bookKeeping.type;

import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.TypeChanges;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelType {
    public static void insertType(String name) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("INSERT INTO TYPECHANGES VALUES (1,'" + name + "')");
    }

    public static ArrayList<TypeChanges> selectType() {
        ArrayList<TypeChanges> list = new ArrayList<>();
        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM TYPECHANGES");

            while (resultSet.next()) {
                //list.add(new TypeChanges(resultSet.getString(1), resultSet.getString(2)));
            }
            connect.closeConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void deleteType(String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("DELETE FROM TYPECHANGES WHERE ID = '" + id + "'");
        connect.closeConnect();
    }
}
