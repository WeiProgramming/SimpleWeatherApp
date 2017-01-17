package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Util.Utils;
import model.Place;
import model.Weather;

/**
 * Created by Wei on 1/15/17.
 */
public class JSONWeatherParser {
    public static Weather getWeather(String data){
        Weather weather = new Weather();
        //create JSON object from data
        try {
            JSONObject jsonObject = new JSONObject(data);

            Place place = new Place();

            JSONObject coodObj = Utils.getObject("coord",jsonObject);
            place.setLatitude(Utils.getFloat("lat",coodObj));
            place.setLongitude(Utils.getFloat("long",coodObj));

            //get sys obj
            JSONObject sysObj = Utils.getObject("sys", jsonObject); //jumping back to parent and query till you find sys
            place.setCountry(Utils.getString("country", sysObj));
            place.setLastupdate(Utils.getInt("dt",jsonObject)); //going back parent cause "dt" is outside of sysobj
            place.setSunrise(Utils.getInt("sunrise",sysObj));
            place.setSunset(Utils.getInt("sunset",sysObj));
            place.setCity(Utils.getString("name",jsonObject));
            weather.place = place;

            JSONArray jsonArray = jsonObject.getJSONArray("weather");// @param the tagName from the json api
            JSONObject jsonWeather = jsonArray.getJSONObject(0); //0 means we are interested of the obj at json array 0
            weather.currentCondition.setWeatherID(Utils.getInt("id", jsonObject));
            weather.currentCondition.setDescription(Utils.getString("description", jsonWeather));
            weather.currentCondition.setCondition(Utils.getString("main",jsonWeather));
            weather.currentCondition.setIcon(Utils.getString("icon",jsonWeather));

            JSONObject windObj = Utils.getObject("wind",jsonObject);
            weather.wind.setSpeed(Utils.getFloat("speed",windObj));
            weather.wind.setDegrees(Utils.getFloat("deg",windObj));

            JSONObject cloudObj = Utils.getObject("clouds",jsonObject);
            weather.clouds.setPrecipitation(Utils.getInt("all",cloudObj));

            return weather; //weather class is the hub for all the data
        } catch (JSONException e) {
            e.printStackTrace();

            return null;
        }
    }
}
