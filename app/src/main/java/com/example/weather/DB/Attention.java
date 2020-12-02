package com.example.weather.DB;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class Attention extends LitePalSupport {
    private int id;
    private String AttentionCityName;
    private int AttentionCityCode;
    private int ProvinceID;

    public int getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(int provinceID) {
        ProvinceID = provinceID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return AttentionCityName;
    }

    public void setCityName(String cityName) {
        AttentionCityName = cityName;
    }

    public int getCityCode() {
        return AttentionCityCode;
    }

    public void setCityCode(int cityCode) {
        AttentionCityCode = cityCode;
    }
}
