package cn.dgl.www.step.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqliteHelper extends SQLiteOpenHelper {

      public static final String TAG = "MySqliteHelper";

      public static final String CREATE_STUDENT = "create table t_student (" +

                 "id integer primary key, name varchar(20), gender varchar(10), age integer)";

      public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,

                            int version) {

           super(context, name, factory, version);

      }

      @Override

      public void onOpen(SQLiteDatabase db) {

           Log.i(TAG,"open db");

           super.onOpen(db);

      }

      @Override

      public void onCreate(SQLiteDatabase db) {

           Log.i(TAG,"create db");

           Log.i(TAG,"before excSql");

//           db.execSQL(CREATE_STUDENT);

           Log.i(TAG,"after excSql");

      }

      @Override

      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


      }

}