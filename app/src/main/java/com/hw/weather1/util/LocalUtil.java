package com.hw.weather1.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.hw.weather1.db.WeatherDataDBHelper;
import com.hw.weather1.model.City;
import com.hw.weather1.model.Country;
import com.hw.weather1.model.Province;
import com.hw.weather1.model.WeatherDataDB;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by hw on 2016/2/20.
 */
public class LocalUtil {
    public synchronized static void LoadAllLocation(Context context) {
        String localAddress = "ChinaLocationData.txt";
        FileInputStream input = null;
        BufferedReader reader = null;
        try {
            input = context.openFileInput(localAddress);
            reader = new BufferedReader(new InputStreamReader(input));
            StringBuffer locationJsonData = new StringBuffer();
            String line;

            while(null != (line = reader.readLine())) {
                locationJsonData.append(line);
            }

            WeatherDataDB db = WeatherDataDB.getDbInstance(context);
            JSONObject locationJsonObj = new JSONObject((locationJsonData.toString()));
            JSONArray ChinaJsonArr = locationJsonObj.getJSONArray("list");

            JSONObject provinceJson = null;
            JSONObject cityJson = null;
            JSONObject countryJson = null;
            ContentValues provinceValue = new ContentValues();
            ContentValues cityValue = new ContentValues();
            ContentValues countryValue = new ContentValues();
            Province province = new Province();
            City city = new City();
            Country country = new Country();
            JSONArray cityJsonList;
            JSONArray countryJsonList;

            for(int i = 0 ; i < ChinaJsonArr.length(); i++) {
                provinceJson = ChinaJsonArr.getJSONObject(i);
                //province.SetProvinceCode(provinceJson.getString("id"));
                province.SetProvinceCode(null);
                province.SetProvinceName(provinceJson.getString("name"));
                province.SetProvinceEn(provinceJson.getString("en"));

                db.saveProvince(province);
                cityJsonList = provinceJson.getJSONArray("list");
                for(int j = 0; j < cityJsonList.length(); j++) {
                    cityJson = cityJsonList.getJSONObject(j);
                    //city.SetCityCode(cityJson.getString("id"));
                    city.SetCityCode(null);
                    city.SetCityName(cityJson.getString("name"));
                    city.SetCityEn(cityJson.getString("en"));
                    city.SetBelongProvinceEn(provinceJson.getString("en"));

                    db.saveCity(city);
                    countryJsonList = cityJson.getJSONArray("list");

                    for(int k = 0; k < countryJsonList.length(); k++) {
                        countryJson = countryJsonList.getJSONObject(k);
                        country.SetCountryCode(countryJson.getString("id"));
                        country.SetCountryName(countryJson.getString("name"));
                        country.SetCountryEn(countryJson.getString("en"));
                        country.SetBelongCityEn(cityJson.getString("en"));

                        db.saveCountry(country);
                        countryJson = null;
                    }
                    cityJson = null;
                }
                provinceJson = null;
            }


        }catch (Exception e) {
            Log.e("LocalUtil", "error");
            e.printStackTrace();
        }finally {
            try {
                if (null != input) {
                    input.close();
                }
                if(null != reader) {
                    reader.close();
                }
            }catch (Exception e) {
                Log.e("close", "fail");
                e.printStackTrace();
            }
        }
    }
}
