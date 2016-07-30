package com.diploma.form.windows.bookKeeping.keeping;


import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.BookKeeping;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelKeeping {
    public static void insertKeeping(String begin, String end) {
        try {
            Connect connect = new Connect();
            connect.getStatement().executeUpdate("INSERT INTO BOOKKEEPING VALUES (1,TO_DATE('" + begin + "', 'YYYY-MM-DD'),TO_DATE('" + end + "', 'YYYY-MM-DD '))");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<BookKeeping> selectKeeping() {
        ArrayList<BookKeeping> list = new ArrayList<>();
        try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT ID,TO_CHAR(DATEBEGIN,'YYYY-MM-DD'),TO_CHAR(DATEEND,'YYYY-MM-DD') FROM BOOKKEEPING");

            while (resultSet.next()) {
                //list.add(new BookKeeping(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3)));
            }
            connect.closeConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void deleteKeeping(String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("DELETE FROM BOOKKEEPING WHERE ID = '" + id + "'");
        connect.closeConnect();
    }
}
