package com.example.androidlearning.storage;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "<DB>";
    private static final String DATABASE_NAME = "test.db";
    private static final int VERSION_CODE = 1;

    public MyDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION_CODE);
    }

    /**
     * 如果只有一个数据库名称，那么这个数据库的位置会在私有目录中
     * 如果带有SD卡路径，那么数据库位置在指定路径下
     *
     * @param context 上下文
     * @param name    数据库名称
     * @param factory 如果为null，则提供默认游标
     * @param version 标本号，默认1
     */
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public MyDatabaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "db onCreate");
        //将表的创建放在这里面完成
        //_id,SimpleCursorAdapter要求表格必须有_id
        String sql = "create table info_tb(_id integer primary key autoincrement," +
                "name varchar(20) not null," +
                "age integer," +
                "isMan boolean," +
                "number integer)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "db onUpgrade");
    }

    public boolean insert(String name, String age, Boolean isMan, Integer number) {
        //第一次调用这个方法时，会调用onCreate方法，创建好数据库文件，之后不会再调用
        SQLiteDatabase db = getWritableDatabase();
        String sql = "insert into info_tb (name,age,isMan,number) values(?,?,?,?)";
        db.execSQL(sql, new String[]{name, age, isMan == null ? null : (isMan ? "true" : "false"), String.valueOf(number)});
        return true;
    }

    public Cursor search() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select * from info_tb";
        return db.rawQuery(sql, null);
    }
}
