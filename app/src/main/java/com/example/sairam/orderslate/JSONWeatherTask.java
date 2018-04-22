package com.example.sairam.orderslate;



import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import org.json.JSONException;

public class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

    private MainActivity ma;
    JSONWeatherTask(MainActivity ma){
        this.ma = ma;
    }
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
    @Override
    protected void onPostExecute(Weather weather) {
        super.onPostExecute(weather);

        if (weather.iconData != null && weather.iconData.length > 0) {
            Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
           // imgView.setImageBitmap(img);
        }
        ma.temperature.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "\u00b0"+"C");
        ma.humidity.setText("" + weather.currentCondition.getHumidity() + "%");

//        cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
//        condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
//        temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "�C");
//        hum.setText("" + weather.currentCondition.getHumidity() + "%");
//        press.setText("" + weather.currentCondition.getPressure() + " hPa");
//        windSpeed.setText("" + weather.wind.getSpeed() + " mps");
//        windDeg.setText("" + weather.wind.getDeg() + "�");

    }
}

