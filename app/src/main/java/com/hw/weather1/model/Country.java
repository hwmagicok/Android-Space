package com.hw.weather1.model;

/**
 * Created by hw on 2016/2/15.
 */
public class Country {
    private String CountryName;
    private int CountryId;
    private int belongCityId;

    public Country() {

    }

    public Country(final String name, final int id, final int cityId) {
        if(null != name && 0 <= id && 0 <= cityId) {
            CountryName = name;
            CountryId = id;
            belongCityId = cityId;
        }
    }

    public String GetCountryName() {
        return CountryName;
    }

    public int GetCountryId() {
        return CountryId;
    }

    public int GetBelongCityId() {
        return belongCityId;
    }

    public boolean SetCountryName(final String name){
        if(null != name){
            CountryName = name;
            return true;
        }
        return false;
    }

    public void SetCountryId(final int id) {
        CountryId = id;
    }

    public void SetBelongCityId(final int id) {
        belongCityId = id;
    }
}
