package com.diploma.dataBase.sql;

import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.Ground;
import com.diploma.dataBase.tables.Sensor;
import com.diploma.dataBase.tables.Wave;

import java.sql.SQLException;
import java.util.ArrayList;

public class Select {
    public static ArrayList<Ground> selectGround() throws SQLException {
        Connect connect = new Connect();
        ArrayList<Ground> arrayList = new ArrayList<>();
       /* ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM GROUND");
        while (resultSet.next()) {
            arrayList.add(new Ground(resultSet.getString(1), resultSet.getString(2)));
        }
        connect.closeConnect();*/
        return arrayList;
    }


    public static ArrayList<Wave> selectWave() throws SQLException {
        Connect connect = new Connect();
        ArrayList<Wave> arrayList = new ArrayList<>();
        /*ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM WAVE ORDER BY TIMEWAVE");
        while (resultSet.next()) {
            arrayList.add(new Wave(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
        }
        connect.closeConnect();*/
        return arrayList;
    }

    public static ArrayList<Wave> selectWave(String where) throws SQLException {
        Connect connect = new Connect();
        ArrayList<Wave> arrayList = new ArrayList<>();
       /* ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM WAVE WHERE " + where + " ORDER BY TIMEWAVE");
        while (resultSet.next()) {
            arrayList.add(new Wave(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7)));
        }
        connect.closeConnect();*/
        return arrayList;
    }

    public static ArrayList<Sensor> selectSensor() throws SQLException {
        Connect connect = new Connect();
        ArrayList<Sensor> arrayList = new ArrayList<>();
        /*ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM SENSOR ORDER BY ID");
        while (resultSet.next()) {
            arrayList.add(new Sensor(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        connect.closeConnect();*/
        return arrayList;
    }
}
