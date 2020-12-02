package com.example.weather.DB;

import org.litepal.crud.LitePalSupport;

public class County extends LitePalSupport {
    private int id;
    private String CountyName;
    private int CountyCode;
    private int CityID;
    private String WeatherID;

    public int getCityID() {
        return CityID;
    }

    public void setCityID(int cityID) {
        CityID = cityID;
    }

    public String getWeatherID() {
        return WeatherID;
    }

    public void setWeatherID(String weatherID) {
        WeatherID = weatherID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountyName() {
        return CountyName;
    }

    public void setCountyName(String countyName) {
        CountyName = countyName;
    }

    public int getCountyCode() {
        return CountyCode;
    }

    public void setCountyCode(int countyCode) {
        CountyCode = countyCode;
    }
}
