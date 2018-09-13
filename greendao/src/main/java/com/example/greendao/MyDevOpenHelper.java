package com.example.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.greendao.greendao.CourseDao;
import com.example.greendao.greendao.DaoMaster;
import com.example.greendao.greendao.StudentDao;
import com.orhanobut.logger.Logger;

import org.greenrobot.greendao.database.Database;

/**
 * Created by derik on 18-9-13.
 */

public class MyDevOpenHelper extends DaoMaster.DevOpenHelper{
    public MyDevOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        //数据备份
        Logger.d("oldversion=" + oldVersion + "; newVersion" + newVersion);
        db.execSQL("create table " + "temp_" + CourseDao.TABLENAME + " as select * from " + CourseDao.TABLENAME + ";");
        db.execSQL("create table " + "temp_" + StudentDao.TABLENAME + " as select * from " + StudentDao.TABLENAME + ";");
        db.execSQL("DROP TABLE IF EXISTS " + CourseDao.TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS " + StudentDao.TABLENAME);
        onCreate(db);
        db.execSQL("insert into " + CourseDao.TABLENAME + " (_id,NAME,TEACHER_NAME,HOURS) select _id,NAME,TEACHER_NAME,HOURS from " + "temp_" + CourseDao.TABLENAME + ";");
        db.execSQL("insert into " + StudentDao.TABLENAME + " select * from " + "temp_" + StudentDao.TABLENAME + ";");

        //删除临时表单
        db.execSQL("DROP TABLE IF EXISTS temp_" + CourseDao.TABLENAME);
        db.execSQL("DROP TABLE IF EXISTS temp_" + StudentDao.TABLENAME);
    }
}
