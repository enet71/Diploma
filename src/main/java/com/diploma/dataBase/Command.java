package com.diploma.dataBase;

import java.sql.SQLException;

public class Command {
    public static void insert(String sql) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate(sql);
        connect.closeConnect();
    }


}
