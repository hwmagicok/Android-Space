package com.hw.weather1.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.hw.weather1.db.WeatherDataDBHelper;

import java.util.ArrayList;

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
            String provinceEn = province.GetProvinceEn();

            ContentValues content = new ContentValues();
            content.put("province_name", provinceName);
            content.put("province_code", provinceCode);
            content.put("province_en", provinceEn);
            db.insert("Province", null, content);
        }
    }

    public void saveCity(final City city) {
        if(null != city) {
            String cityName = city.GetCityName();
            String cityCode = city.GetCityCode();
            String cityEn = city.GetCityEn();
            String belongProvince = city.GetBelongProvinceEn();

            ContentValues content = new ContentValues();
            content.put("city_name", cityName);
            content.put("city_code", cityCode);
            content.put("city_en", cityEn);
            content.put("belong_province", belongProvince);

            db.insert("City", null ,content);
        }
    }

    public void saveCountry(final Country country) {
        if(null != country) {
            String countryName = country.GetCountryName();
            String countryCode = country.GetCountryCode();
            String countryEn = country.GetCountryEn();
            String belongCity = country.GetBelongCityEn();

            ContentValues content = new ContentValues();
            content.put("country_name", countryName);
            content.put("country_code", countryCode);
            content.put("country_en", countryEn);
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
        String[] columnTmp = new String[] {"province_en"};
        Cursor cursor = db.query("Province",columnTmp, "province_name = ?", new String[] {provinceName}, null, null, null);
        if(null != cursor) {
            if(cursor.moveToFirst()) {
                String provinceEn = cursor.getString(cursor.getColumnIndex("province_en"));
                columnTmp[0] = "city_name";
                cursor = db.query("City", columnTmp, "belong_province = ?", new String[]{provinceEn}, null, null, null);
            }
            //cursor.close();
        }
        return cursor;
    }

    public Cursor queryCountry(final String cityName) {
        String[] columnTmp = new String[] {"city_en"};
        Cursor cursor = db.query("City", columnTmp, "city_name = ?", new String[] {cityName}, null, null, null);
        if(null != cursor) {
            if(cursor.moveToFirst()) {
                String cityEn = cursor.getString(cursor.getColumnIndex("city_en"));
                columnTmp[0] = "country_name";
                cursor = db.query("Country", columnTmp, "belong_city = ?", new String[] {cityEn}, null, null, null);
            }
            //cursor.close();
        }
        return cursor;
    }

    /*此函数是用来将特别行政区和直辖市下的各类城市直接剥离出来显示
      不再显示在“特别行政区”和“直辖市”层级下
      函数名带Special的都是此目的
     */
    public ArrayList<String> querySpecialProvince() {
        String[] columnTmp = new String[] {"city_name"};
        String[] specialProvinceEn = new String[] {"Main", "S.A.R."};
        /*
        Cursor cursor = db.query("City", columnTmp, "belong_province = ? or belong_province = ?",
                specialProvinceEn, null, null, null);
        */
        Cursor cursor = db.rawQuery("select city_name from City " +
                "where belong_province = ? or belong_province = ? ", specialProvinceEn);

        ArrayList<String> cityList = new ArrayList<String>();
        if(cursor.moveToFirst()) {
            do {
                cityList.add(cursor.getString(cursor.getColumnIndex("city_name")));
            }while (cursor.moveToNext());
        }
        return cityList;
    }


}
