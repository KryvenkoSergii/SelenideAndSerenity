package com.softserve.utils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonFileReader {

    private static String jsonFilePath = "src\\resources\\endpoints.json";

    public static String getJsonValueByKey(String key) {
        JSONParser parser = new JSONParser();

        try (Reader reader = new FileReader(jsonFilePath)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            return (String) jsonObject.get(key);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "error: wrong key";
    }

}