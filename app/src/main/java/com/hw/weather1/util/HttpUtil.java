package com.hw.weather1.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by hw on 2016/2/16.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address) {
        if(null != address) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    HttpURLConnection connection = null;
                    try {
                        Log.e("sendHttpRequest","in");
                        URL url = new URL(address);
                        connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");
                        connection.setReadTimeout(15000);
                        connection.setConnectTimeout(15000);

                        Log.e("sendHttpRequest", "open connection");

                        InputStream in = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                        Log.e("sendHttpRequest","stream reader");
                        StringBuffer response = new StringBuffer();
                        String line = new String();

                        int i = 0;
                        while((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        Log.e("sendHttpRequest", response.toString());
                        Log.e("sendHttpRequest","out");
                    }catch (Exception e) {
                        Log.e("HttpUtil", "error");
                        e.printStackTrace();
                    }finally {
                        if(null != connection) {
                            connection.disconnect();
                        }
                    }
                }
            }).start();
        }
    }
}
