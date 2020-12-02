package com.example.weather.DB;

import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

public class Province extends LitePalSupport {
    private int ID;
    private String ProvinceName;
    private int ProvinceID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProvinceName() {
        return ProvinceName;
    }

    public void setProvinceName(String provinceName) {
        ProvinceName = provinceName;
    }

    public int getProvinceID() {
        return ProvinceID;
    }

    public void setProvinceID(int provinceID) {
        ProvinceID = provinceID;
    }
}
