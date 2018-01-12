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
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import java.util.List;

import cn.dgl.www.step.R;
import cn.dgl.www.step.sqlite.CenterWeatherCityCode;
import cn.dgl.www.step.sqlite.WeatherCityDao;
import cn.dgl.www.step.sqlite.WeatherData;
import cn.dgl.www.step.view.WeatherChartView;


/**
 * Created by dugaolong on 17/12/28.
 */

public class WeatherShowActivity extends AppCompatActivity implements View.OnClickListener{

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
    private ImageView iv_right;
    private TextView tianqi, wendu, feng, shidu, feels_like, pm25, qiya, nengjiandu, richu,riluo, kongqizl;
    private TextView desc1, desc2, desc3, desc4, desc5, desc6;
    WeatherChartView chartView ;
    LinearLayout six_future_ll,six_future_ll_night;

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
        progressDialog.setMessage("加载中...");
        progressDialog.show();
    }

    private void initView() {
        iv_right = (ImageView) findViewById(R.id.iv_right);
        iv_right.setOnClickListener(this);
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
        chartView= (WeatherChartView) findViewById(R.id.line_char);
        six_future_ll = (LinearLayout) findViewById(R.id.six_future_ll);
        six_future_ll_night = (LinearLayout) findViewById(R.id.six_future_ll_night);
        desc1 = (TextView) findViewById(R.id.desc1);
        desc2 = (TextView) findViewById(R.id.desc2);
        desc3 = (TextView) findViewById(R.id.desc3);
        desc4 = (TextView) findViewById(R.id.desc4);
        desc5 = (TextView) findViewById(R.id.desc5);
        desc6 = (TextView) findViewById(R.id.desc6);
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
        List<WeatherData.WeatherBean.FutureBean> futureBeanList= weatherBean.getFuture();
        //白天的天气
        for(int i = 0;i<6;i++)
        {
            //动态加载view
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
            linearLayout.setLayoutParams(llParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            six_future_ll.addView(linearLayout);
            //添加TextView
            //设置宽高以及权重
            LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView textView1 = new TextView(this);
            //设置textview垂直居中
            textView1.setGravity(Gravity.CENTER);
            textView1.setLayoutParams(tvParams);
            textView1.setText(i==0?"今天":(i==1?"明天":futureBeanList.get(i).getDay()));
            linearLayout.addView(textView1);
            TextView textView2 = new TextView(this);
            //设置textview垂直居中
            textView2.setGravity(Gravity.CENTER);
            textView2.setLayoutParams(tvParams);
            String date = futureBeanList.get(i).getDate();
            textView2.setText(date.substring(date.length()-2,date.length())+"日");
            linearLayout.addView(textView2);
            TextView textView3 = new TextView(this);
            //设置textview垂直居中
            textView3.setGravity(Gravity.CENTER);
            textView3.setLayoutParams(tvParams);
            String text = futureBeanList.get(i).getText();
            String wea1 = text.substring(0,text.indexOf("/"));
            textView3.setText(wea1);
            linearLayout.addView(textView3);
        }
        //温度折线图
        int[] highs = new int[6];
        int[] lows = new int[6];
        for(int i = 0; i <6;i++)
        {
            highs[i] = Integer.parseInt(futureBeanList.get(i).getHigh());
            lows[i] = Integer.parseInt(futureBeanList.get(i).getLow());
        }
        // 设置白天温度曲线
        chartView .setTempDay(highs);
        // 设置夜间温度曲线
        chartView .setTempNight(lows);
        chartView .invalidate();
        //晚上的天气
        for(int i = 0;i<6;i++)
        {
            //动态加载view
            LinearLayout linearLayout = new LinearLayout(this);
            LinearLayout.LayoutParams llParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,1);
            linearLayout.setLayoutParams(llParams);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setPadding(10,10,10,10);
            six_future_ll_night.addView(linearLayout);
            //添加TextView
            //设置宽高以及权重
            LinearLayout.LayoutParams tvParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            TextView textView1 = new TextView(this);
            //设置textview垂直居中
            textView1.setGravity(Gravity.CENTER);
            textView1.setLayoutParams(tvParams);
            String text = futureBeanList.get(i).getText();
            String wea2 = text.substring(text.indexOf("/")+1,text.length());
            textView1.setText(wea2);
            linearLayout.addView(textView1);
            TextView textView2 = new TextView(this);
            //设置textview垂直居中
            textView2.setLayoutParams(tvParams);
            textView2.setText(futureBeanList.get(i).getWind().replace("风力",""));
            textView2.setGravity(Gravity.CENTER);
            linearLayout.addView(textView2);
        }
        //生活指数
        WeatherData.WeatherBean.TodayBean.SuggestionBean suggestionBean = weatherBean.getToday().getSuggestion();
        desc1.setText(suggestionBean.getCar_washing().getBrief()+"");
        desc2.setText(suggestionBean.getDressing().getBrief()+"");
        desc3.setText(suggestionBean.getFlu().getBrief()+"");
        desc4.setText(suggestionBean.getSport().getBrief()+"");
        desc5.setText(suggestionBean.getTravel().getBrief()+"");
        desc6.setText(suggestionBean.getUv().getBrief()+"");
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
        builder.setCancelable(false);
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

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.iv_right:
                gotoSelectCity();
                break;
        }

    }
}
