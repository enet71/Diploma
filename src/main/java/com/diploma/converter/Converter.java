package com.diploma.converter;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Converter {
    private static NumberFormat formatter = new DecimalFormat("#0.00");


    public static void updateData() {
        try {
            URL connection = new URL("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");
            HttpURLConnection httpURLConnection;
            httpURLConnection = (HttpURLConnection) connection.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            InputStream in = httpURLConnection.getInputStream();
            OutputStream out = new FileOutputStream("src/main/resources/converter/converter.json");

            byte buffer[] = new byte[265];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.flush();
            out.close();
            in.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static double EURUAHBuy(Double EUR) {
        return Double.parseDouble(formatter.format(EUR * getValue("EUR", "buy")));
    }

    public static double EURUAHSale(Double EUR) {
        return Double.parseDouble(formatter.format(EUR * getValue("EUR", "sale")));
    }

    public static double RUBUAHBuy(Double RUR) {
        return Double.parseDouble(formatter.format(RUR * getValue("RUR", "buy")));
    }

    public static double RUBUAHSale(Double RUR) {
        return Double.parseDouble(formatter.format(RUR * getValue("RUR", "sale")));
    }

    public static double USDUAHBuy(Double USD) {
        return Double.parseDouble(formatter.format(USD * getValue("USD", "buy")));
    }

    public static double USDUAHSale(Double USD) {
        return Double.parseDouble(formatter.format(USD * getValue("USD", "sale")));
    }

    public static double BTCUAHBuy(Double BTC) {
        return Double.parseDouble(formatter.format(BTC * getValue("BTC", "buy")));
    }

    public static double BTCUAHSale(Double BTC) {
        return Double.parseDouble(formatter.format(BTC * getValue("BTC", "sale")));
    }

    public static double UAHEURBuy(Double UAH) {
        return Double.parseDouble(formatter.format(UAH * getValue("EUR", "buy")));
    }

    public static double UAHEURSale(Double UAH) {
        return Double.parseDouble(formatter.format(UAH * getValue("EUR", "sale")));
    }

    public static double UAHRUBBuy(Double UAH) {
        return Double.parseDouble(formatter.format(UAH * getValue("RUR", "buy")));
    }

    public static double UAHRUBSale(Double UAH) {
        return Double.parseDouble(formatter.format(UAH * getValue("RUR", "sale")));
    }

    public static double UAHUSDBuy(Double UAH) {
        return Double.parseDouble(formatter.format(UAH * getValue("USD", "buy")));
    }

    public static double UAHUSDSale(Double UAH) {
        return Double.parseDouble(formatter.format(UAH * getValue("USD", "sale")));
    }

    public static double UAHBTCBuy(Double UAH) {
        return Double.parseDouble(formatter.format(UAH * getValue("BTC", "buy")));
    }

    public static double UAHBTCSale(Double UAH) {
        return Double.parseDouble(formatter.format(UAH / getValue("BTC", "sale")));
    }
    private static double getValue(String ccy, String operation) {
        try {
            FileReader fileReader = new FileReader("src/converter/converter.json");
            BufferedReader reader = new BufferedReader(fileReader);
            String s = reader.readLine();
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(s);
            JSONArray array = (JSONArray) obj;

            for (Object anArray : array) {
                JSONObject obj2 = new JSONObject(String.valueOf(anArray));
                if (obj2.getString("ccy").equals(ccy)) {
                    return obj2.getDouble(operation);
                }
            }
        } catch (IOException | ParseException | JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
