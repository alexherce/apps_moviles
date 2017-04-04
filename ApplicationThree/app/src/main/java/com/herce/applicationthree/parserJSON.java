package com.herce.applicationthree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by herce on 2/9/17.
 */

public class parserJSON {
    public static ArrayList<Auto> Autos = new ArrayList<>();

    public static Auto parseaObjeto(JSONObject obj) {
        try {
            Auto auto = new Auto();

            auto.setMarca(obj.getString("marca"));
            auto.setAuto(obj.getString("auto"));
            auto.setImagen(obj.getString("imagen"));
            return auto;

        } catch (JSONException e1){
            e1.printStackTrace();
            return null;
        }
    }

    public static ArrayList<Auto> parseaArreglo(JSONArray arr) {
        JSONObject obj = null;
        Auto auto = null;
        Autos.clear();

        try {
            for(int i = 0; i < arr.length(); i++) {
                obj = arr.getJSONObject(i);
                auto = new Auto();

                auto.setMarca(obj.getString("marca"));
                auto.setAuto(obj.getString("auto"));
                auto.setImagen(obj.getString("imagen"));

                Autos.add(auto);
            }
            return Autos;
        } catch (JSONException e1) {
            e1.printStackTrace();
            return null;
        }
    }
}
