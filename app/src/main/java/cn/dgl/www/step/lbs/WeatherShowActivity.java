package cn.dgl.www.step.lbs;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;

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

public class WeatherShowActivity extends AppCompatActivity {

    private WeatherData mWeatherData = null;
    private String city;
    private TextView tv_title, publish_time;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    String re = (String) msg.obj;
//                    weather_data.setText(re);
                    Log.i("SSSSSSSSSSS", "re:" + re);
                    break;
                case 1:
                    WeatherData wd = (WeatherData) msg.obj;
                    Log.i("SSSSSSSSSSS", "wd:" + wd.toString());
                    setData();
                    progressDialog.dismiss();
                    break;
            }
        }
    };

    private String townID;
    private String ak = "2llxErTgU6XQd2DxTGe35Q7M4hfjqLMG";
    private String SHA1 = "39:EA:65:EC:CA:EC:F8:82:E3:6C:14:F5:C4:B3:50:43:A9:B7:AE:0A";
    ProgressDialog progressDialog;
    //更新时间
    private String lastUpdate;
    private TextView tianqi, wendu, feng, shidu, feels_like, pm25, qiya, nengjiandu, richu,riluo, kongqizl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_weather);
        initProgressBar();
        getData();
        initView();
        getCityByCityName(city);
    }

    private void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle.containsKey("city")) {
            city = bundle.getString("city");
        }
    }

    private void initProgressBar() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    private void initView() {
        tianqi = (TextView) findViewById(R.id.tianqi);
        tv_title = (TextView) findViewById(R.id.tv_title);
        publish_time = (TextView) findViewById(R.id.publish_time);
        wendu = (TextView) findViewById(R.id.wendu);
        feng = (TextView) findViewById(R.id.feng);
        shidu = (TextView) findViewById(R.id.shidu);
        feels_like = (TextView) findViewById(R.id.feels_like);
        pm25 = (TextView) findViewById(R.id.pm25);
        qiya = (TextView) findViewById(R.id.qiya);
        nengjiandu = (TextView) findViewById(R.id.nengjiandu);
        richu = (TextView) findViewById(R.id.richu);
        riluo = (TextView) findViewById(R.id.riluo);
        kongqizl = (TextView) findViewById(R.id.kongqizl);
    }

    private void setData() {
        tv_title.setText(city);
        WeatherData.WeatherBean weatherBean = mWeatherData.getWeather().get(0);
        lastUpdate = weatherBean.getLast_update();
        publish_time.setText(lastUpdate.substring(0, 10) + "  最近更新：" + lastUpdate.substring(11, 19) + "");
        WeatherData.WeatherBean.NowBean nowBean = weatherBean.getNow();
        tianqi.setText(nowBean.getText());
        wendu.setText(nowBean.getTemperature()+"°");
        feng.setText(nowBean.getWind_direction()+"风\n"+nowBean.getWind_speed());
        shidu.setText(nowBean.getHumidity()+"%\n空气湿度");
        feels_like.setText("体感温度："+weatherBean.getNow().getFeels_like()+"°");
        pm25.setText(nowBean.getAir_quality().getCity().getPm25()+"\npm2.5指数");
        qiya.setText(nowBean.getPressure()+"百帕\n气压");
        nengjiandu.setText(nowBean.getVisibility()+"KM\n能见度");
        richu.setText(weatherBean.getToday().getSunrise()+"\n日出");
        riluo.setText(weatherBean.getToday().getSunset()+"\n日落");
        kongqizl.setText(nowBean.getAir_quality().getCity().getQuality()+"\n空气质量");
    }

    public void getCityByCityName(String city) {
        WeatherCityDao dao = new WeatherCityDao(getApplicationContext());
        if (city.endsWith("市")) {
            city = city.substring(0, city.length() - 1);
        } else if (city.endsWith("地区")) {
            city = city.substring(0, city.length() - 2);
        } else if (city.endsWith("自治州")) {
            city = city.substring(0, city.length() - 3);
        }
        CenterWeatherCityCode cwcc = dao.getCityByCityName(city);
        if (cwcc != null) {
            townID = cwcc.getTownID();
            Log.i("SSSSSSSSSSS", "townID==" + townID);
            Log.i("SSSSSSSSSSS", "city==" + city);
            sendRequestWithHttpClient();
        } else {
            //地区不存在，提示重新选择
            showokDialog();
        }
    }

    //提示重新选择
    private void showokDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(WeatherShowActivity.this);
        builder.setTitle("我的天");
        builder.setMessage("当前地区不存在");
        builder.setPositiveButton("重新选择", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                gotoSelectCity();
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void gotoSelectCity() {
        startActivity(new Intent(WeatherShowActivity.this, SelectCityActivity.class));
        finish();
    }

    private void sendRequestWithHttpClient() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
//                    URL url = new URL("http://api.map.baidu.com/telematics/v3/weather?location=" + city
//                            + "&output=json&ak=" + WeatherShowActivity.this.ak +
//                            "&mcode=" + SHA1 + ";" + WeatherShowActivity.this.getPackageName() + "");
//                    URL url = new URL("http://www.sojson.com/open/api/weather/json.shtml?city=西安");
                    URL url = new URL("http://tj.nineton.cn/Heart/index/all?city=" + townID + "&language=zh-chs&unit=c&aqi=city&alarm=1&key=78928e706123c1a8f1766f062bc8676b");
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
                    Log.i("SSSSSSSSSSS", "response:" + response.toString());
                    WeatherData weatherData = JSON.parseObject(response.toString(), WeatherData.class);
                    if (weatherData != null) {
                        setSp(response.toString());
                        WeatherShowActivity.this.mWeatherData = weatherData;
                    }

                    String jsonString = JSON.toJSONString(weatherData);
                    Log.i("SSSSSSSSSSS", "jsonString:" + jsonString);
                    Message message = new Message();
                    message.what = 1;
                    message.obj = weatherData;
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

    private void setSp(String response) {
        SharedPreferences preferences = getSharedPreferences("step", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("weatherData", response);
        editor.commit();
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
                    Log.i("SSSSSSSSSSS", "count:" + count);
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
    }


    public static String unicode2String(String unicode) {
        if (TextUtils.isEmpty(unicode)) return null;
        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = unicode.indexOf("\\u", pos)) != -1) {
            sb.append(unicode.substring(pos, i));
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }

        return sb.toString();
    }
}
