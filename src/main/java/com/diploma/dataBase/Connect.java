package com.diploma.dataBase;

import com.diploma.staticField.SettingFields;

import java.sql.*;

@Deprecated
public class Connect {
    private Connection connection;
    private Statement statement = null;

    public Connect() {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String port = SettingFields.PORT;//"49856";
            String server = SettingFields.SERVER;//"localhost";
            String sid = "orcl";
            String password = SettingFields.PASSWORD;//"555792";
            String userid = SettingFields.LOGIN;//"c##diplom";
            String jdbcUrl = "jdbc:oracle:thin:@" + server + ":" + port + ":" + sid;

            connection = DriverManager.getConnection(jdbcUrl, userid, password);
            if (getConnectStatus()) {
                statement = connection.createStatement();
            }
        } catch (SQLException e) {
            System.out.println("SQLException" + e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public boolean getConnectStatus() {
        return connection != null;
    }

    public Statement getStatement() {
        return statement;
    }


    public void closeConnect(ResultSet resultSet) {
        try {
            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Connect close");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void closeConnect() {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
