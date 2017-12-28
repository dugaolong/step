package cn.dgl.www.step.lbs;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


public class Test2 extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub  
        super.onCreate(savedInstanceState);
        setContentView(cn.dgl.www.step.R.layout.test);
        openGPSSettings();

    }

    private void openGPSSettings() {
        LocationManager alm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (alm.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
//            Toast.makeText(this, "GPS模块正常", Toast.LENGTH_SHORT).show();
            doWork();
            return;
        }

        Toast.makeText(this, "请开启GPS！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        startActivityForResult(intent, 0); //此为设置完成后返回到获取界面

    }

    private void doWork() {
        String msg = "";
        TextView textView = (TextView) findViewById(cn.dgl.www.step.R.id.tv1);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        Criteria criteria = new Criteria();
        // 获得最好的定位效果  
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(false);
        // 使用省电模式  
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        // 获得当前的位置提供者  
        String provider = locationManager.getBestProvider(criteria, true);
        // 获得当前的位置  
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "请添加获取位置权限", Toast.LENGTH_LONG).show();
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);
        Geocoder gc = new Geocoder(this);
        List<Address> addresses = null;
        try {
            addresses = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block  
            e.printStackTrace();
        }
        if (addresses.size() > 0) {
            msg += "AddressLine：" + addresses.get(0).getAddressLine(0) + "\n";
            msg += "CountryName：" + addresses.get(0).getCountryName() + "\n";
            msg += "Locality：" + addresses.get(0).getLocality() + "\n";
            msg += "FeatureName：" + addresses.get(0).getFeatureName();
        }
        textView.setText(msg);
    }

}  