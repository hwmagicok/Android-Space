package com.hw.weather1.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.hw.weather1.R;
import com.hw.weather1.util.HttpUtil;

/**
 * Created by hw on 2016/2/16.
 */
public class SelectCityActivity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.response);

        String address = "http://www.thinkpage.cn/weather/api/cities";
        //String address = "https://api.heweather.com/x3/citylist?search=allchina&key=8d52bc3c89964e5290c5cdc3e28f626e";
        HttpUtil.sendHttpRequest(address);
        //TextView text = (TextView) findViewById(R.id.response_text);
    }
}
