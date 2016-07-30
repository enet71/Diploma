package com.diploma.form.windows.data.add.ground;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.Connect;
import com.diploma.dataBase.sql.Select;
import com.diploma.dataBase.tables.Ground;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModelGround {
    public static ArrayList<Ground> selectGround() throws SQLException {
        return Select.selectGround();
    }
    public static void insertGround(String name) throws SQLException {
        Command.insert("INSERT INTO GROUND VALUES (NULL,'" + name  + "')");
    }

    public static void delete(String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("DELETE FROM GROUND WHERE ID = '" + id + "'");
        connect.closeConnect();
    }
}
