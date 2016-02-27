package com.hw.weather1.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hw.weather1.db.WeatherDataDBHelper;

/**
 * Created by hw on 2016/2/19.
 */
public class WeatherDataDB {
    private static WeatherDataDB weatherDataDB = null;
    private SQLiteDatabase db = null;
    private String dbName = "hw_weatherDB";
    private int version = 1;

    private WeatherDataDB(Context context) {
        WeatherDataDBHelper dbHelper = new WeatherDataDBHelper(context, dbName, null, version);
        db = dbHelper.getWritableDatabase();
    }

    public synchronized static WeatherDataDB getDbInstance(Context context) {
        if(null == weatherDataDB) {
            weatherDataDB = new WeatherDataDB(context);
        }
        return weatherDataDB;
    }

    public void saveProvince(final Province province) {
        if(null != province) {
            String provinceName = province.GetProvinceName();
            String provinceCode = province.GetProvinceCode();
            ContentValues content = new ContentValues();
            content.put("province_name", provinceName);
            content.put("province_code", provinceCode);
            db.insert("Province", null, content);
        }
    }

    public void saveCity(final City city) {
        if(null != city) {
            String cityName = city.GetCityName();
            String cityCode = city.GetCityCode();
            String belongProvinceCode = city.GetBelongProvinceCode();

            ContentValues content = new ContentValues();
            content.put("city_name", cityName);
            content.put("city_code", cityCode);
            content.put("belong_province", belongProvinceCode);

            db.insert("City", null ,content);
        }
    }

    public void saveCountry(final Country country) {
        if(null != country) {
            String countryName = country.GetCountryName();
            String countryCode = country.GetCountryCode();
            String belongCity = country.GetBelongCityCode();

            ContentValues content = new ContentValues();
            content.put("country_name", countryName);
            content.put("country_code", countryCode);
            content.put("belong_city", belongCity);

            db.insert("Country", null, content);
        }
    }

    public Cursor queryProvince() {
        String[] columnTmp = new String[] {"province_name"};
        Cursor cursor = db.query("Province", columnTmp, null, null, null, null, null);
        //cursor.close();
        return cursor;
    }

    public Cursor queryCity(final String provinceName) {
        String[] columnTmp = new String[] {"province_code"};
        Cursor cursor = db.query("Province",columnTmp, "province_name = ?", new String[] {provinceName}, null, null, null);
        if(null != cursor) {
            if(cursor.moveToFirst()) {
                String provinceCode = cursor.getString(cursor.getColumnIndex("province_code"));
                columnTmp[0] = "city_name";
                cursor = db.query("City", columnTmp, "belong_province = ?", new String[]{provinceCode}, null, null, null);
            }
            //cursor.close();
        }
        return cursor;
    }

    public Cursor queryCountry(final String cityName) {
        String[] columnTmp = new String[] {"city_code"};
        Cursor cursor = db.query("City", columnTmp, "city_name = ?", new String[] {cityName}, null, null, null);
        if(null != cursor) {
            if(cursor.moveToFirst()) {
                String cityCode = cursor.getString(cursor.getColumnIndex("city_code"));
                columnTmp[0] = "country_name";
                cursor = db.query("Country", columnTmp, "belong_city = ?", new String[] {cityCode}, null, null, null);
            }
            //cursor.close();
        }
        return cursor;
    }
}
