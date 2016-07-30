package com.diploma.localization;

import com.diploma.localization.ru.TableNameRU;
import com.diploma.localization.ru.add.Sensor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Start {
    public static getName TABLENAME = new TableNameRU();
    public static getName SENSORNAME = new Sensor();
    private static File file;
    private static JSONObject jsonObject;

    public static void RU() {
        load("src/main/resources/localization/ru.json");
    }

    public static void UA() {
        load("src/main/resources/localization/ua.json");
    }

    public static void EN() {
        load("src/main/resources/localization/en.json");
    }


    public static void load(String path) {
        file = new File(path);

        read();
        Localization.CHART = jsonObject.get("CHART").toString();
        Localization.SENSOR = jsonObject.get("SENSOR").toString();
        Localization.REGION = jsonObject.get("REGION").toString();
        Localization.SEISMIC = jsonObject.get("SEISMIC").toString();
        Localization.GROUND = jsonObject.get("GROUND").toString();
        Localization.TABLE = jsonObject.get("TABLE").toString();
        Localization.CHART = jsonObject.get("CHART").toString();
    }

    private static void read() {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);
            JSONParser parser = new JSONParser();
            jsonObject = (JSONObject) parser.parse(reader);
            reader.close();
            fileReader.close();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }


}
