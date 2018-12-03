package com.example.dagger2;

import android.app.Application;

import com.example.dagger2.components.AppComponent;
import com.example.dagger2.components.DaggerAppComponent;
import com.example.dagger2.module.AppModule;

/**
 * Created by Derik on 2018/9/26.
 * Email: weilai0314@163.com
 */

public class Dagger2Application extends Application {

    private static AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public static AppComponent getAppComponent(){
        return mAppComponent;
    }

}
