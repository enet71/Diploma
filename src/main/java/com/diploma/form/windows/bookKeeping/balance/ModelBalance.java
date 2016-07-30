package com.diploma.form.windows.bookKeeping.balance;

import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.BalanceChanges;

import java.sql.SQLException;
import java.util.ArrayList;

public class ModelBalance {
    public static void insertBalance(String value, String bookkeping, String typechanges) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("INSERT INTO BALANCECHANGES VALUES (1," + value + "," + bookkeping + "," + typechanges + ",0)");
    }

    public static ArrayList<BalanceChanges> selectBalance() {
        ArrayList<BalanceChanges> list = new ArrayList<>();
        /*try {
            Connect connect = new Connect();
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM BALANCECHANGES");

            while (resultSet.next()) {
                list.add(new BalanceChanges(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4),resultSet.getString(4)));
            }
            connect.closeConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/
        return list;
    }

    public static void deleteBalance(String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("DELETE FROM BALANCECHANGES WHERE ID = '" + id + "'");
        connect.closeConnect();
    }
}
