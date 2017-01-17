package Util;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Wei on 1/15/17.
 * where all the static variables will be referenced
 */
public class Utils {
    //after "=" we need to specify the location of weather report which will be dynamic
    public static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";
    public static final String ICON_URL =  "http://aopenweathermap.org/img/w/"; //gets icon from openweather

    public static JSONObject getObject(String tagName, JSONObject jsonObject)throws JSONException{
        JSONObject jObj = jsonObject.getJSONObject(tagName);
        return jObj;
    }
    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException{
        return (float) jsonObject.getDouble(tagName);
    }
    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }

}
