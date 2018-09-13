package com.example.greendao;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.greendao.DaoMaster;
import com.example.greendao.greendao.DaoSession;

/**
 * Created by derik on 18-9-12.
 */

public class MyApplication extends Application {
    private static MyApplication INSTANCE;
    private DaoSession mDaoSession;
    private SQLiteDatabase mDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        setDatabase();
    }

    private void setDatabase() {
        MyDevOpenHelper devOpenHelper = new MyDevOpenHelper(getApplicationContext(), "green.db");
        mDatabase = devOpenHelper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(mDatabase);
        mDaoSession = daoMaster.newSession();
    }

    public static MyApplication getInstance(){
        return INSTANCE;
    }

    public DaoSession getDaoSession(){
        return mDaoSession;
    }

    public SQLiteDatabase getDatabase(){
        return mDatabase;
    }
}
