package cn.dgl.www.step.app;

import android.app.Application;

import com.xiaomi.ad.AdSdk;

/**
 * Created by yuandl on 2016-10-18.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        AdSdk.initialize(this, "2882303761517625658");
    }
}
