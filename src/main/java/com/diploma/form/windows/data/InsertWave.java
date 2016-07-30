package com.diploma.form.windows.data;

import com.diploma.dataBase.Connect;

import java.sql.SQLException;
import java.util.Date;

public class InsertWave {
    public static void main(String[] args) throws SQLException {



        insertWaves(100,"p",142);
        insertWaves(100,"s",142);

    }

    public static void insertWaves(int n, String type,int idSensor) throws SQLException {
        Date date = new Date();

        for (int i = 0; i < n; i++) {
            date.setSeconds(i);
            System.out.println(date.toString());
            Connect connect = new Connect();
            int a = (int) (Math.random() * 30);
            int c = (int) (Math.random() * 30) + 40;
            connect.getStatement().executeUpdate("" +
                    "INSERT INTO WAVE " +
                    "VALUES (NULL ," + idSensor + ",TO_DATE('" + date.getYear() + "/" + date.getMonth() + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "', 'YYYY/MM/DD HH24:MI:SS'),800," + a + ",'" + type + "'," + c + ")");

            date.setSeconds(++i);
            int b = (int) (Math.random() * 30) - 30;
            c = (int) (Math.random() * 30) - 40;
            connect.getStatement().executeUpdate("" +
                    "INSERT INTO WAVE " +
                    "VALUES (NULL ," + idSensor + ",TO_DATE('" + date.getYear() + "/" + date.getMonth() + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "', 'YYYY/MM/DD HH24:MI:SS'),800," + b + ",'" + type +"'," + c + ")");
            connect.closeConnect();
        }


        for (int i = n; i < n *2; i++) {
            date.setSeconds(i);
            Connect connect = new Connect();
            int a = (int) (Math.random() * 100);
            int c = (int) (Math.random() * 30) + 40;
            connect.getStatement().executeUpdate("" +
                    "INSERT INTO WAVE " +
                    "VALUES (NULL ," + idSensor + ",TO_DATE('" + date.getYear() + "/" + date.getMonth() + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "', 'YYYY/MM/DD HH24:MI:SS'),800," + a + ",'" + type +"'," + c + ")");


            date.setSeconds(++i);
            int b = (int) (Math.random() * 100) - 100;
            c = (int) (Math.random() * 30) - 40;
            connect.getStatement().executeUpdate("" +
                    "INSERT INTO WAVE " +
                    "VALUES (NULL ," + idSensor + ",TO_DATE('" + date.getYear() + "/" + date.getMonth() + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "', 'YYYY/MM/DD HH24:MI:SS'),800," + b + ",'" + type +"'," + c + ")");
            connect.closeConnect();
        }


        for (int i = n * 2; i < n * 3; i++) {
            date.setSeconds(i);
            System.out.println(date.toString());
            Connect connect = new Connect();
            int a = (int) (Math.random() * 30);
            int c = (int) (Math.random() * 30) + 40;
            connect.getStatement().executeUpdate("" +
                    "INSERT INTO WAVE " +
                    "VALUES (NULL ," + idSensor + ",TO_DATE('" + date.getYear() + "/" + date.getMonth() + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "', 'YYYY/MM/DD HH24:MI:SS'),800," + a + ",'" + type + "'," + c + ")");

            date.setSeconds(++i);
            int b = (int) (Math.random() * 30) - 30;
            c = (int) (Math.random() * 30) - 40;
            connect.getStatement().executeUpdate("" +
                    "INSERT INTO WAVE " +
                    "VALUES (NULL ," + idSensor + ",TO_DATE('" + date.getYear() + "/" + date.getMonth() + "/" + date.getDate() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds() + "', 'YYYY/MM/DD HH24:MI:SS'),800," + b + ",'" + type +"'," + c + ")");
            connect.closeConnect();
        }
    }
}
