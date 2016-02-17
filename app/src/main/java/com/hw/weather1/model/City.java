package com.hw.weather1.model;

/**
 * Created by hw on 2016/2/15.
 */
public class City {
    private String CityName;
    private int CityId;
    private int belongProvinceId;

    public City() {

    }

    public City(final String name, final int id, final int provinceId) {
        if(null != name && 0 <= id && 0 <= provinceId){
            CityName = name;
            CityId = id;
            belongProvinceId = provinceId;
        }
    }

    public String GetCityName() {
        return CityName;
    }

    public int GetCityId() {
        return CityId;
    }

    public int GetBelongProvinceId() {
        return belongProvinceId;
    }

    public boolean SetCityName(final String name){
        if(null != name){
            CityName = name;
            return true;
        }
        return false;
    }

    public void SetCityId(final int id) {
        CityId = id;
    }

    public void SetBelongProvinceId(final int id) {
        belongProvinceId = id;
    }
}
