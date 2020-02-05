package com.comapny.greendaodemo;

import android.app.Application;

import com.comapny.greendaodemo.DaoMaster;
import com.comapny.greendaodemo.DaoSession;

import org.greenrobot.greendao.database.Database;


public class MyApplication extends Application {

    private DaoMaster.DevOpenHelper openHelper;
    private Database readableDb;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        openHelper = new DaoMaster.DevOpenHelper(this, "student-info.db");//数据库名称
        readableDb = openHelper.getWritableDb();

        daoSession = new DaoMaster(readableDb).newSession();

    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
