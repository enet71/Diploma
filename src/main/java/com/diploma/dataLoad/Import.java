package com.diploma.dataLoad;

import com.diploma.dataBase.Connect;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class Import {
    public static void dataFile(File file) throws IOException, SQLException {

        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);

        String[] strMas;
        String s;
        Connect connect = new Connect();
        while ((s = reader.readLine()) != null) {
            strMas = s.split("#");
            connect.getStatement().executeUpdate("" +
                    "INSERT INTO WAVE (ID,SENSOR, TIMEWAVE, DEPTHWAVE, ENERGY, TYPEWAVE, MAGNITUDE) " +
                    "VALUES ('" +
                    strMas[0] + "','" +
                    strMas[1] +
                    "',TO_DATE('" + strMas[2] + "','yyyy-mm-dd hh24:mi:ss'),'" +
                    strMas[3] + "','" +
                    strMas[4] + "','" +
                    strMas[5] + "','" +
                    strMas[6] + "')");
        }
        connect.closeConnect();
        reader.close();
        fileReader.close();
    }
}
