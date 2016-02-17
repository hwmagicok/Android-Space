package com.hw.weather1.model;

/**
 * Created by hw on 2016/2/15.
 */
public class Province {
    private String ProvinceName;
    private int ProvinceId;

    public Province() {

    }

    public Province(final String name, final int id) {
        if(null != name && 0 <= id) {
            ProvinceName = name;
            ProvinceId = id;
        }
    }

    public String GetProvinceName() {
        return ProvinceName;
    }

    public int GetProvinceId() {
        return ProvinceId;
    }

    public boolean SetProvinceName(final String name) {
        if(null != name) {
            ProvinceName = name;
            return true;
        }
        return false;
    }

    public boolean SetProvinceId(final int id) {
        ProvinceId = id;
        return true;
    }
}
