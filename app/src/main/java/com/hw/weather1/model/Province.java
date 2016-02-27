package com.hw.weather1.model;

/**
 * Created by hw on 2016/2/15.
 */
public class Province {
    private String ProvinceName;
    private String ProvinceCode;

    public Province() {

    }

    public Province(final String name, final String code) {
        if(null != name && 0 <= code.length()) {
            ProvinceName = name;
            ProvinceCode = code;
        }
    }

    public String GetProvinceName() {
        return ProvinceName;
    }

    public String GetProvinceCode() {
        return ProvinceCode;
    }

    public boolean SetProvinceName(final String name) {
        if(null != name) {
            ProvinceName = name;
            return true;
        }
        return false;
    }

    public boolean SetProvinceCode(final String code) {
        ProvinceCode = code;
        return true;
    }
}
