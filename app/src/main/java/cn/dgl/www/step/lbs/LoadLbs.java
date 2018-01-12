package cn.dgl.www.step.lbs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import cn.dgl.www.step.R;
import cn.dgl.www.step.view.CustomPrograssBar;

/**
 * 过度页面，关闭时候
 * 跳转到
 * 1、城市选择页面
 * 2、天气页面
 */
public class LoadLbs extends Activity {

    private CustomPrograssBar customPrograssBar;
    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();

    private String province;
    private String city;
    private String district;
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);
        setContentView(cn.dgl.www.step.R.layout.test);

        showWaitingDialog();//加载提示

        //BDAbstractLocationListener为7.2版本新增的Abstract类型的监听接口
        //原有BDLocationListener接口暂时同步保留。具体介绍请参考后文中的说明
        mLocationClient = new LocationClient(getApplicationContext());
        //声明LocationClient类
        mLocationClient.registerLocationListener(myListener);
        //注册监听函数

        LocationClientOption locationClientOption = new LocationClientOption();
        locationClientOption.setTimeOut(3000);
        locationClientOption.setIsNeedAddress(true);
        //可选，是否需要地址信息，默认为不需要，即参数为false
        //如果开发者需要获得当前点的地址信息，此处必须为true

        mLocationClient.setLocOption(locationClientOption);
        //mLocationClient为第二步初始化过的LocationClient对象
        //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
        //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
        mLocationClient.start();
        //mLocationClient为第二步初始化过的LocationClient对象
        //调用LocationClient的start()方法，便可发起定位请求
    }

    private void showWaitingDialog() {
        if (customPrograssBar != null && customPrograssBar.isShowing()) {
            customPrograssBar.dismiss();
        }
        customPrograssBar = new CustomPrograssBar(LoadLbs.this, R.style.CustomDialog, "loading");
        customPrograssBar.show();
    }

    private void showSuccessDialog() {
        if (customPrograssBar != null && customPrograssBar.isShowing()) {
            customPrograssBar.dismiss();
        }
        showokDialog();
    }

    private void gotoShowWeather() {
        Intent intent = new Intent(LoadLbs.this, WeatherShowActivity.class);
        intent.putExtra("city", city);
        startActivity(intent);
        finish();
    }

    private void showFailedDialog() {
        if (customPrograssBar != null && customPrograssBar.isShowing()) {
            customPrograssBar.dismiss();
        }
        showDialog();
    }

    private void showokDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoadLbs.this);
        builder.setTitle("定位成功");
        builder.setCancelable(false);
        builder.setMessage("当前城市：\n\n"+province+"  "+city+"  "+district);
        builder.setPositiveButton("定位准确", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                gotoShowWeather();
            }
        });
        builder.setNegativeButton("位置错了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //跳转到城市选择界面
                gotoSelectCity();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoadLbs.this);
        builder.setMessage("自动定位失败了，请手动选择城市哦");
        builder.setCancelable(false);
        builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //跳转到城市选择界面
                gotoSelectCity();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void gotoSelectCity() {
        startActivity(new Intent(LoadLbs.this, SelectCityActivity.class));
        finish();
    }


    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //此处的BDLocation为定位结果信息类，通过它的各种get方法可获取定位相关的全部结果
            //以下只列举部分获取地址相关的结果信息
            //更多结果信息获取说明，请参照类参考中BDLocation类中的说明

            String addr = location.getAddrStr();    //获取详细地址信息
            String country = location.getCountry();    //获取国家
            province = location.getProvince();    //获取省份
            city = location.getCity();    //获取城市
            district = location.getDistrict();    //获取区县
            String street = location.getStreet();    //获取街道信息
            Log.i("SSSSSSSSSSS", "addr：" + addr + "\n"
                    + "country：" + country + "\n"
                    + "province：" + province + "\n"
                    + "city：" + city + "\n"
                    + "district：" + district + "\n"
                    + "street：" + street + "\n"
            );
            if (TextUtils.isEmpty(city)) {
                Log.i("SSSSSSSSSSS", "定位失败");
                showFailedDialog();
            } else {
                Log.i("SSSSSSSSSSS", "定位成功");
                showSuccessDialog();
            }


        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.stop();
        }
    }


}  