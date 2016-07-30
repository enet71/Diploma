package com.diploma.form.windows.maps;

import com.diploma.dataBase.Connect;
import com.diploma.dataBase.tables.Sensor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
    public static ArrayList<Coord> getCoord() throws SQLException {
        ArrayList<Coord> arrayList = new ArrayList<>();
        Connect connect = new Connect();
        ResultSet resultSet = connect.getStatement().executeQuery("SELECT LNG,LAT FROM SENSOR");
        while (resultSet.next()){
            arrayList.add(new Coord(resultSet.getString(1),resultSet.getString(2)));
        }
        connect.closeConnect();
        return arrayList;
    }

    public static ArrayList<Sensor> selectSensor(String id) throws SQLException {
        Connect connect = new Connect();
        ArrayList<Sensor> arrayList = new ArrayList<>();
        ResultSet resultSet = connect.getStatement().executeQuery("SELECT * FROM SENSOR WHERE SEISMIC = " + id);
        while (resultSet.next()) {
           // arrayList.add(new Sensor(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6)));
        }
        connect.closeConnect();
        return arrayList;
    }

    public static ArrayList<String> selectSeismicId(){
        ArrayList<String> arrayList = new ArrayList<>();
        Connect connect = new Connect();
        try {
            ResultSet resultSet;
            resultSet = connect.getStatement().executeQuery("SELECT ID FROM SEISMIC");
            while (resultSet.next()){
                arrayList.add(resultSet.getString(1));
            }
            connect.closeConnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
