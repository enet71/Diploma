package com.diploma.initialize.jsonParser;

import com.diploma.staticField.SettingFields;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Load {
    private static File file = new File("src/main/resources/data/setting.json");
    private static JSONObject jsonObject;

    public static void load() {
        read();
        SettingFields.LANGUAGE = jsonObject.get("LANGUAGE").toString();
        SettingFields.PORT = jsonObject.get("PORT").toString();
        SettingFields.SERVER = jsonObject.get("SERVER").toString();
        SettingFields.WIDTH = Integer.parseInt(jsonObject.get("WIDTH").toString());
        SettingFields.HEIGHT = Integer.parseInt(jsonObject.get("HEIGHT").toString());
        SettingFields.RECONNECTDB = Integer.parseInt(jsonObject.get("RECONNECTDB").toString());
        SettingFields.RECONNECTINTERNET = 5000;
        SettingFields.ENTRY = jsonObject.get("ENTRY").toString();
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
