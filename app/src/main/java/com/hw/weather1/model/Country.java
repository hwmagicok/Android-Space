package com.hw.weather1.model;

/**
 * Created by hw on 2016/2/15.
 */
public class Country {
    private String CountryName;
    private String belongCityCode;
    private String CountryCode;

    public Country() {

    }

    public Country(final String name, final String cityCode, String countryCode) {
        if(null != name && null != belongCityCode && null != countryCode) {
            CountryName = name;
            belongCityCode = cityCode;
            CountryCode = countryCode;
        }
    }

    public String GetCountryName() {
        return CountryName;
    }

    public String GetBelongCityCode() {
        return belongCityCode;
    }

    public String GetCountryCode() {
        return CountryCode;
    }

    public boolean SetCountryName(final String name){
        if(null != name && 0 < name.length()){
            CountryName = name;
            return true;
        }
        return false;
    }

    public boolean SetBelongCityCode(final String code) {
        if(null != code && 0 < code.length()) {
            belongCityCode = code;
            return true;
        }
        return false;
    }

    public boolean SetCountryCode(String countryCode) {
        if(null != countryCode && 0 < countryCode.length()) {
            CountryCode = countryCode;
            return true;
        }
        return false;
    }
}
