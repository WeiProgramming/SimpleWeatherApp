package data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import Util.Utils;

/**
 * Created by Wei on 1/15/17.
 */
public class WeatherHttpClient {
    public String getWeatherData(String place){
        HttpURLConnection connection = null;
        InputStream inputStream = null;

        try {//put between try and catch cause connection might not work
            connection = (HttpURLConnection) new URL(Utils.BASE_URL + place).openConnection(); //connection to the web
            connection.setRequestMethod("GET"); //knocking on door to get data
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //reads response
            StringBuffer stringBuffer = new StringBuffer(); //container to store the data
            inputStream = connection.getInputStream(); //steam of bits and data traveling to your device
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = null;

            while((line = bufferedReader.readLine())!=null){
                stringBuffer.append(line+"\r\n"); //organizes the lines
            }
            inputStream.close();
            connection.disconnect();

            return stringBuffer.toString(); //everything converted to string


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; //if doesn't work
    }
}
