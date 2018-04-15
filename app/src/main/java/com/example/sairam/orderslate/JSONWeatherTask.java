package com.example.sairam.orderslate;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONException;

public class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

    @Override
    protected Weather doInBackground(String... params) {
        Weather weather = new Weather();



        String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

        try {
            weather = JSONWeatherParser.getWeather(data);

            // Let's retrieve the icon
            weather.iconData = ((new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return weather;

    }
}

