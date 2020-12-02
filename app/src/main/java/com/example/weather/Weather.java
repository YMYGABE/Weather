package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.DB.Attention;
import com.example.weather.gson.ForecastWeather;
import com.example.weather.gson.NowWeather;
import com.example.weather.util.HttpUtil;
import com.example.weather.util.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class Weather extends AppCompatActivity {

    public List<Attention> attentionList = new ArrayList<>();
    public DrawerLayout drawerLayout;
    private Button selectButton;
    public SwipeRefreshLayout swipeRefresh;
    private String mWeatherId;
    private ScrollView weatherLayout;
    private TextView titleCity;
    private TextView titleUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout forecastLayout;
    private Button AttentionButton;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
//        AttentionButton = (Button) findViewById(R.id.GetAttention);
        selectButton = (Button) findViewById(R.id.select_button);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimaryDark);
        weatherLayout = (ScrollView) findViewById(R.id.weather_layout);
        titleCity = (TextView) findViewById(R.id.cityName);
        titleUpdateTime = (TextView) findViewById(R.id.update_time);
        degreeText = (TextView) findViewById(R.id.degree_text);
        weatherInfoText = (TextView) findViewById(R.id.weather_info_text);
        forecastLayout = (LinearLayout) findViewById(R.id.forecast_layout);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherString = preferences.getString("weather",null);
        String FweatherString = preferences.getString("Fweather",null);
        if(weatherString != null){
            NowWeather weather = Utility.handleNowWeatherResponse(weatherString);
//            System.out.println(weather.getLives().get(0).getCity());
            mWeatherId = weather.getLives().get(0).getAdcode();
            showNowWeatherinfo(weather);
        }
        if (FweatherString != null){
            ForecastWeather forecastWeather = Utility.handleForecastWeatherResponse(FweatherString);
            mWeatherId = forecastWeather.getForecasts().get(0).getAdcode();
            showForecastWeatherInfo(forecastWeather);
        }
        else{
            mWeatherId = getIntent().getStringExtra("weather_id");
//            String weatherId = getIntent().getStringExtra("weather_id");
            weatherLayout.setVisibility(View.INVISIBLE);
            requestForecastWeather(mWeatherId);
            requestNowWeather(mWeatherId);

        }
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestNowWeather(mWeatherId);
                requestForecastWeather(mWeatherId);
            }
        });
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
//
//        AttentionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Attention attention = new Attention();
//                attention.setCityName(titleCity.getText().toString());
//                attention.setCityCode(Integer.parseInt(mWeatherId));
//                attention.save();
//                Toast.makeText(Weather.this,"关注成功",Toast.LENGTH_SHORT).show();
//                AttentionButton.setBackgroundColor(Color.parseColor("#FF0000"));
//                AttentionButton.setText("已关注");
//            }
//        });
    }
    public void requestNowWeather(final String weatherId){
        String nowWeatherUrl = "https://restapi.amap.com/v3/weather/weatherInfo?city="+weatherId+"&key=1473ebe9f66d858d1ad1dd2a43b4b552";
        HttpUtil.sendOkHttpRequest(nowWeatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Weather.this,"获取天气信息失败",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final NowWeather weather = Utility.handleNowWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        System.out.println("status"+weather.status);
                        if (weather != null){
                            SharedPreferences.Editor editor = PreferenceManager.
                                    getDefaultSharedPreferences(Weather.this).edit();
                            editor.putString("weather",responseText);
                            editor.apply();
                            mWeatherId = weather.getLives().get(0).getAdcode();
                            showNowWeatherinfo(weather);
                        }
                        else{
                            Toast.makeText(Weather.this,"获取天气信息失败",Toast.LENGTH_SHORT).show();
                        }
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
    }
    public void requestForecastWeather(final String weatherId){
        String nowWeatherUrl = "https://restapi.amap.com/v3/weather/weatherInfo?city="+weatherId+"&extensions=all&key=1473ebe9f66d858d1ad1dd2a43b4b552";
        HttpUtil.sendOkHttpRequest(nowWeatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(Weather.this,"获取未来天气信息失败",
                                Toast.LENGTH_SHORT).show();
                        System.out.println("失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final ForecastWeather weather = Utility.handleForecastWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null){
                            SharedPreferences.Editor editor = PreferenceManager.
                                    getDefaultSharedPreferences(Weather.this).edit();
                            editor.putString("Fweather",responseText);
                            editor.apply();
                            mWeatherId = weather.getForecasts().get(0).getAdcode();
                            showForecastWeatherInfo(weather);
                            swipeRefresh.setRefreshing(false);
                        }
                        else{
                            Toast.makeText(Weather.this,"获取未来天气信息失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void showNowWeatherinfo(NowWeather weather){
        String cityName = weather.getLives().get(0).getCity();
        String updateTime = weather.getLives().get(0).getReporttime();
        String degree = weather.getLives().get(0).getTemperature() + "°C";
        String weatherInfo = weather.getLives().get(0).getWeather();
        titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        weatherLayout.setVisibility(View.VISIBLE);
    }

    private void showForecastWeatherInfo(ForecastWeather weather){
        forecastLayout.removeAllViews();
        for (ForecastWeather.ForecastsBean.CastsBean forecast : weather.getForecasts().get(0).getCasts()){
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item,
                    forecastLayout,false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            dateText.setText(forecast.getDate());
            infoText.setText(forecast.getDayweather());
            maxText.setText(forecast.getDaytemp());
            minText.setText(forecast.getNighttemp());
            forecastLayout.addView(view);
        }
        weatherLayout.setVisibility(View.VISIBLE);
    }
}