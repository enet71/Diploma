package com.diploma.form.windows.data.add.sensor;

import com.diploma.dataBase.Command;
import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.Sensor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModelSensor {
    public static void insertSensor(String seismic, String ground, String name, String lng, String lat) throws SQLException {
        Command.insert("INSERT INTO SENSOR VALUES (NULL,'" + seismic + "','" + ground + "','" + name + "','" + lng + "','" + lat + "')");
    }

    public static void editSensor(String seismic, String ground, String name, String lng, String lat, String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("" +
                "UPDATE SENSOR " +
                "SET SEISMIC = " + seismic + ",GROUND = " + ground + ",SENSOR.NAME = '" + name + "',LNG = '" + lng + "',LAT = '" + lat + "' " +
                "WHERE ID = " + id );
        //Command.insert("UPDATE SENSOR SET SEISMIC = '" + seismic + "',GROUND = '" + ground + "',NAME = '" + name + "',LNG = '" + lng + "',LAT = '" + lat + "') WHERE ID = '" + id + "'");
    }

    public static ArrayList<Sensor> selectSensor() throws SQLException {
        Connect connect = new Connect();
        ArrayList<Sensor> arrayList = new ArrayList<>();
        ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM SENSOR");
        while (resultSet.next()) {
            //arrayList.add(new Sensor(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        connect.closeConnect();
        return arrayList;
    }

    public static ArrayList<Sensor> selectSensorName() throws SQLException {
        Connect connect = new Connect();
        ArrayList<Sensor> arrayList = new ArrayList<>();
        ResultSet resultSet = connect.getStatement().executeQuery("" +
                "SELECT SENSOR.ID,SEISMIC.NAME,GROUND.NAME,SENSOR.NAME,LNG,LAT " +
                "FROM SENSOR " +
                "INNER JOIN SEISMIC ON SEISMIC.ID = SENSOR.SEISMIC " +
                "INNER JOIN GROUND ON GROUND.ID = SENSOR.GROUND");
        while (resultSet.next()) {
            //arrayList.add(new Sensor(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        connect.closeConnect();
        return arrayList;
    }

    public static void delete(String id) throws SQLException {
        Connect connect = new Connect();
        connect.getStatement().executeUpdate("DELETE FROM SENSOR WHERE ID = '" + id + "'");
        connect.closeConnect();
    }
}
