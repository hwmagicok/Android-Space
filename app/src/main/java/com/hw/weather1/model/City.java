package com.hw.weather1.model;

/**
 * Created by hw on 2016/2/15.
 */
public class City {
    private String CityName;
    private String CityCode;
    private String belongProvinceCode;

    public City() {

    }

    public City(final String name, final String code, final String provinceCode) {
        if(null != name && null != code && null != provinceCode){
            CityName = name;
            CityCode = code;
            belongProvinceCode = provinceCode;
        }
    }

    public String GetCityName() {
        return CityName;
    }

    public String GetCityCode() {
        return CityCode;
    }

    public String GetBelongProvinceCode() {
        return belongProvinceCode;
    }

    public boolean SetCityName(final String name){
        if(null != name && 0 < name.length()){
            CityName = name;
            return true;
        }
        return false;
    }

    public boolean SetCityCode(final String code) {
        if(null != code && 0 < code.length()) {
            CityCode = code;
            return true;
        }
        return false;
    }

    public boolean SetBelongProvinceCode(final String code) {
        if(null != code && 0 < code.length()) {
            belongProvinceCode = code;
            return true;
        }
        return false;
    }
}
