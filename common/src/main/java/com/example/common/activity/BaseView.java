package com.example.common.activity;

import android.support.annotation.StringRes;

/**
 * Created by derik on 18-9-8
 * <p>
 * Email: weilai0314@163.com
 */
public interface BaseView {

    void showMsg(String msg);
    void showMsg(@StringRes int msg);
    void showLoading();
    void hideLoading();
    void invalidToken();
    void customFinish();
}
