package com.example.androidlearning.storage.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.androidlearning.storage.greendao.model.DaoMaster;
import com.example.androidlearning.storage.greendao.model.DaoSession;

import org.greenrobot.greendao.database.Database;

public class GreenDaoApplication extends Application {

    public static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        initDB();
    }

    /**
     * 连接数据库并创建会话
     */
    public void initDB() {
        //1.获取需要连接的数据库
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "qing.db");
        Database db = devOpenHelper.getEncryptedWritableDb("password");
        //2.创建数据库链接
        DaoMaster daoMaster = new DaoMaster(db);
        //3.创建指定会话
        daoSession = daoMaster.newSession();
    }
}
