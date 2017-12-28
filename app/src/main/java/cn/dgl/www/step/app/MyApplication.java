package cn.dgl.www.step.app;

import android.app.Application;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yuandl on 2016-10-18.
 */

public class MyApplication extends Application {
    private String TAG = "MyApplication";
    @Override
    public void onCreate() {
        super.onCreate();

        copyDB();
    }

    /**
     * 将 数据库从 assets 复制到 databases下
     */
    private void copyDB() {
        //data/data/packageName/databases/
        File mkdir = new File(getFilesDir().getParent(),"databases");
        //创建 databases文件夹
        if (!mkdir.exists()) mkdir.mkdirs();
        Log.e(TAG, "copyDb: mkdir="+mkdir.getPath());
        //数据库文件
        File file = new File(mkdir,"CenterWeatherCityCode.db");
        //只是在程序第一次启动时创建
        if(!file.exists()){
            //获取 assets管理
            AssetManager assets = getAssets();
            //执行文件复制
            try {
                InputStream open = assets.open("CenterWeatherCityCode.db");
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bs = new byte[1024];
                int len ;
                while ((len = open.read(bs))!=-1){
                    fos.write(bs,0,len);
                }
                fos.flush();
                fos.close();
                open.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.e(TAG, "copyDb: exists="+file.getPath());
    }

}
