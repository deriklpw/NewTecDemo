package com.example.dagger2.components;

import com.example.dagger2.Dagger2Application;
import com.example.dagger2.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Derik on 2018/9/28.
 * Email: weilai0314@163.com
 */

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    Dagger2Application getApplication();
}
