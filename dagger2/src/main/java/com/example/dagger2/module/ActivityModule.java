package com.example.dagger2.module;

import android.content.Context;

import com.example.dagger2.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Derik on 2018/9/28.
 * Email: weilai0314@163.com
 */

@Module
public class ActivityModule {
    private Context mContext;

    public ActivityModule(Context context){
        mContext = context;
    }

    @ActivityScope
    @Provides
    Context provideContext(){
        return mContext;
    }

}
