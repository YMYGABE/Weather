package com.example.weather.util;

import android.text.TextUtils;

import com.example.weather.DB.City;
import com.example.weather.DB.County;
import com.example.weather.DB.Province;
import com.example.weather.gson.ForecastWeather;
import com.example.weather.gson.NowWeather;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Utility {
    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject jsonObject = new JSONObject(response);
                JSONArray temp = jsonObject.getJSONArray("districts");
                JSONObject jsonObject1 = temp.getJSONObject(0);
                JSONArray allProvince = jsonObject1.getJSONArray("districts");
//                System.out.println(jsonObject1);
//                System.out.println(temp);
//                System.out.println(allProvince);
//                String Test = allProvince.getJSONObject(0).toString();
//                System.out.println(Test);
//                System.out.println(allProvince.length());
//                JSONObject provinceObject1 = allProvince.getJSONObject(0);
//                System.out.println(provinceObject1);
//                System.out.println(provinceObject1.getString("name"));
                for (int i = 0;i < allProvince.length();i++){
                    JSONObject provinceObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceID(provinceObject.getInt("adcode"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCityResponse(String response,int provinceID){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray temp = jsonObject.getJSONArray("districts");
                JSONObject jsonObject1 = temp.getJSONObject(0);
                JSONArray allCities = jsonObject1.getJSONArray("districts");
                for(int i = 0;i< allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("adcode"));
                    city.setProvinceID(provinceID);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCountyResponse(String response,int cityID){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONObject jsonObject = new JSONObject(response);
                JSONArray temp = jsonObject.getJSONArray("districts");
                JSONObject jsonObject1 = temp.getJSONObject(0);
                JSONArray allCounty = jsonObject1.getJSONArray("districts");
                for(int i = 0;i < allCounty.length();i++){
                    JSONObject countyObject = allCounty.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCountyCode(countyObject.getInt("adcode"));
//                    county.setWeatherID(countyObject.getString());
                    county.setCityID(cityID);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static NowWeather handleNowWeatherResponse(String response) {
        return new Gson().fromJson(response, NowWeather.class);
    }

    public static ForecastWeather handleForecastWeatherResponse(String response) {
        return new Gson().fromJson(response, ForecastWeather.class);
    }
}
