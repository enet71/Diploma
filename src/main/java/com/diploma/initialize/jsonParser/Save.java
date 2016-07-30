package com.diploma.initialize.jsonParser;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.diploma.staticField.SettingFields;

import java.io.*;

public class Save {
    private static File file = new File("src/main/resources/data/setting.json");
    private static JSONObject jsonObject;

    public static void save(){
        read();
        put("LANGUAGE", SettingFields.LANGUAGE);
        put("PORT", SettingFields.PORT);
        put("SERVER", SettingFields.SERVER);
        put("WIDTH", String.valueOf(SettingFields.WIDTH));
        put("HEIGHT", String.valueOf(SettingFields.HEIGHT));
        put("RECONNECTDB", String.valueOf(SettingFields.RECONNECTDB));
        put("RECONNECTINTERNET", String.valueOf(SettingFields.RECONNECTINTERNET));
        put("ENTRY", SettingFields.ENTRY);
        write();
    }

    public static void main(String[] args) throws IOException, ParseException {
        /*JSONObject obj = new JSONObject();
        obj.put("name", "foo");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));
        FileWriter fileWriter = new FileWriter("setting.json");
        fileWriter.write(obj.toString());
        fileWriter.flush();
        fileWriter.close();
        System.out.print(obj);*/


    }

    private static void save(String attribute, String value) {
        read();
        put(attribute, value);
        write();
    }

    private static void put(String attribute, String value) {
        jsonObject.put(attribute, value);
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

    private static void write() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
