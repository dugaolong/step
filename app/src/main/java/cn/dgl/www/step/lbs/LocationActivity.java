package cn.dgl.www.step.lbs;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.dgl.www.step.R;
import cn.dgl.www.step.sqlite.CenterWeatherCityCode;
import cn.dgl.www.step.sqlite.WeatherCityDao;
import cn.dgl.www.step.sqlite.WeatherData;

/**
 * Created by dugaolong on 17/12/28.
 */

public class LocationActivity extends Activity {

    private TextView location_data, database_data, weather_data;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String re = (String) msg.obj;
                    weather_data.setText(re);
                    break;
                case 1:
                    WeatherData wd = (WeatherData) msg.obj;
                    weather_data.setText(wd.getWeather().get(0).getCity_name());
                    break;
            }
        }
    };
    private String district;
    private String townID;
    private String ak = "2llxErTgU6XQd2DxTGe35Q7M4hfjqLMG";
    private String SHA1 = "39:EA:65:EC:CA:EC:F8:82:E3:6C:14:F5:C4:B3:50:43:A9:B7:AE:0A";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        location_data = (TextView) findViewById(R.id.location_data);
        weather_data = (TextView) findViewById(R.id.weather_data);
        database_data = (TextView) findViewById(R.id.database_data);

        //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
        //原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        LocationClientOption option = new LocationClientOption();

        option.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(option);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明

        mLocationClient.start();
        //mLocationClient为第二步初始化过的LocationClient对象
        //调用LocationClient的start()方法，便可发起定位请求

    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            String province = location.getProvince();    //获取省份
            String city = location.getCity();    //获取城市
            String district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            location_data.setText("addr：" + addr + "\n"
                    + "country：" + country + "\n"
                    + "province：" + province + "\n"
                    + "city：" + city + "\n"
                    + "district：" + district + "\n"
                    + "street：" + street + "\n"
            );
            getCityByCityName(district);
            sendRequestWithHttpClient();
        }
    }

    public void getCityByCityName(String district){
        WeatherCityDao dao = new WeatherCityDao(getApplicationContext());
        district = district.substring(0,district.length()-1);
        CenterWeatherCityCode cwcc = dao.getCityByCityName(district);
        if(cwcc !=null){
            townID = cwcc.getTownID();
        }
        database_data.setText(
                 "district：" + district + "\n"+
                 "TownID()：" + cwcc.getTownID());
    }
    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
//                    URL url = new URL("http://api.map.baidu.com/telematics/v3/weather?location=" + city
//                            + "&output=json&ak=" + LocationActivity.this.ak +
//                            "&mcode=" + SHA1 + ";" + LocationActivity.this.getPackageName() + "");
//                    URL url = new URL("http://www.sojson.com/open/api/weather/json.shtml?city=西安");
                    URL url = new URL("http://tj.nineton.cn/Heart/index/all?city="+townID+"&language=zh-chs&unit=c&aqi=city&alarm=1&key=78928e706123c1a8f1766f062bc8676b");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    Log.i("TAG", response.toString());
                    WeatherData wd = JSON.parseObject(response.toString(), WeatherData.class);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = wd;
                    handler.sendMessage(message);


//                    parseJSONObjectOrJSONArray(unicode2String(response.toString()));
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //解析JSON数据
    private void parseJSONObjectOrJSONArray(String jsonData) {
        Log.i("TAG", jsonData);
        try {
            String count = "";
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("results");
            if (jsonArray.length() > 0) {
                JSONObject object = jsonArray.getJSONObject(0);
                String city = object.optString("currentCity");
                JSONArray array = object.getJSONArray("weather_data");
                for (int i = 0; i < array.length(); i++) {
                    JSONObject jsonObject1 = array.getJSONObject(i);
                    String dateDay = jsonObject1.optString("date");
                    String weather = jsonObject1.optString("weather");
                    String wind = jsonObject1.optString("wind");
                    String temperature = jsonObject1.optString("temperature");
                    count = count + "\n" + dateDay + " " + weather + " " + wind + " " + temperature;
                    Log.i("AAA", count);
                }

                Message message = new Message();
                message.what = 0;
                message.obj = count;
                handler.sendMessage(message);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }

    public static String unicode2String(String unicode){
        if(TextUtils.isEmpty(unicode))return null;
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while((i=unicode.indexOf("\\u", pos)) != -1){
            sb.append(unicode.substring(pos, i));
            if(i+5 < unicode.length()){
                pos = i+6;
                sb.append((char)Integer.parseInt(unicode.substring(i+2, i+6), 16));
            }
        }

        return sb.toString();
    }
}
