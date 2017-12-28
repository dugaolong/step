package cn.dgl.www.step.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class WeatherCityDao {

    private SQLiteOpenHelper helper;
    private Context context;
    public WeatherCityDao(Context context) {
        this.context = context;
    }

    public List<CenterWeatherCityCode> getAllWeatherCity() {
        List<CenterWeatherCityCode> list = new ArrayList<CenterWeatherCityCode>();
        helper = new MySqliteHelper(context, "CenterWeatherCityCode.db", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from t_student", null);
        if (cursor == null) {
            return null;
        }
        while (cursor.moveToNext()) {
            CenterWeatherCityCode stu = new CenterWeatherCityCode(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12));
            Log.i("WeatherCityDao", stu.toString());
            list.add(stu);
        }
        return list;
    }

    public CenterWeatherCityCode getCityByCityName(String cityName) {
        CenterWeatherCityCode cwcc =null;
        helper = new MySqliteHelper(context, "CenterWeatherCityCode.db", null, 1);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("CenterWeatherCityCode",
        new String[]{"countryID","countryName","countryEN","areaID","areaName","areaEN","cityID","cityName","cityEN","townID","townName","townEN"},
                "cityName=?", new String[]{cityName},
                null, null, null);

        if(cursor == null){

            return null;

        }

        while (cursor.moveToNext()) {
            cwcc = new CenterWeatherCityCode(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11));
            Log.i("WeatherCityDao", cwcc.toString());
        }
        return cwcc;
    }


}