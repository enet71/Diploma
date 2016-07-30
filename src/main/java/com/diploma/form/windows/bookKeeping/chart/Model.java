package com.diploma.form.windows.bookKeeping.chart;

import com.diploma.dataBase.Connect;
import com.diploma.dataBase.sql.Select;
import com.diploma.dataBase.tables.Wave;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    public static String SENSOR;

    public static ArrayList<Wave> selectWave() {
        try {
            return Select.selectWave();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Wave> selectPWave() {

        try {
            if (SENSOR != null) {
                return Select.selectWave("TYPEWAVE = 'p' and SENSOR = '" + SENSOR + "'");
            } else {
                return Select.selectWave("TYPEWAVE = 'p'");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Wave> selectSWave() {

        try {
            if (SENSOR != null) {
                return Select.selectWave("TYPEWAVE = 's' and SENSOR = '" + SENSOR + "'");
            } else {
                return Select.selectWave("TYPEWAVE = 's'");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static ArrayList<String> selectSensorId() {
        Connect connect = new Connect();
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            ResultSet resultSet = connect.getStatement().executeQuery("SELECT ID FROM SENSOR ORDER BY ID");
            while (resultSet.next()) {
                arrayList.add(resultSet.getString(1));
            }
            connect.closeConnect();
            return arrayList;
        } catch (SQLException e) {

        }
        return null;
    }
}
