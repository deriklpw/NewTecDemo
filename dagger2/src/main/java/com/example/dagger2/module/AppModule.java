package com.example.dagger2.module;

import com.example.dagger2.Dagger2Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Derik on 2018/9/28.
 * Email: weilai0314@163.com
 */
@Module
public class AppModule {
    private Dagger2Application dagger2Application;
    public AppModule(Dagger2Application application){
        dagger2Application = application;
    }

    @Singleton
    @Provides
    Dagger2Application provideDagger2Application(){
        return dagger2Application;
    }
}
