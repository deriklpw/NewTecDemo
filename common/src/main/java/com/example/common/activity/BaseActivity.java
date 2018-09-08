package com.example.common.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.derik.library.view.MsgToast;
import com.example.common.R;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    /**
     * 通用的ToolBar标题
     */
    private TextView mCommonTitleTv;
    /**
     * 通用的ToolBar
     */
    private Toolbar mCommonToolbar;
    /**
     * 内容区域
     */
    private ViewGroup mContent;
    private ViewStub mViewStub;
    private AlertDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
    }

    private void initView() {
        mCommonTitleTv = (TextView) findViewById(R.id.common_title_tv);
        mCommonToolbar = (Toolbar) findViewById(R.id.common_title_tb);
        mViewStub = (ViewStub) findViewById(R.id.viewStub);

        setSupportActionBar(mCommonToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 子类调用，重新设置Toolbar
     *
     * @param layout
     */
    public void setToolBar(int layout) {
        hideToolBar();
        mCommonToolbar = (Toolbar) mContent.findViewById(layout);
        mCommonTitleTv = null;
        setSupportActionBar(mCommonToolbar);
        //设置actionBar的标题是否显示，对应ActionBar.DISPLAY_SHOW_TITLE。
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    /**
     * 隐藏ToolBar，可通过setToolBar重新定制ToolBar
     */
    public void hideToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    /**
     * 显示ToolBar
     */
    public void showToolBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.show();
        }
    }

    /**
     * menu的点击事件
     *
     * @param onclick
     */
    public void setToolBarMenuOnclick(Toolbar.OnMenuItemClickListener onclick) {
        mCommonToolbar.setOnMenuItemClickListener(onclick);
    }

    /**
     * 设置左上角back按钮
     */
    public void setBackArrow() {
        final Drawable upArrow = getResources().getDrawable(android.R.drawable.arrow_up_float);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar == null) {
            return;
        }
        //给ToolBar设置左侧的图标
        actionBar.setHomeAsUpIndicator(upArrow);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        actionBar.setDisplayHomeAsUpEnabled(true);
        //设置返回按钮的点击事件
        mCommonToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 设置toolbar下面内容区域的内容
     *
     * @param layoutId
     */
    public void setContentLayout(int layoutId) {
        mViewStub.setLayoutResource(layoutId);
        mViewStub.inflate();
        mContent = findViewById(R.id.content);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            if (mCommonTitleTv != null) {
                mCommonTitleTv.setText(title);
            } else {
                mCommonToolbar.setTitle(title);
            }
        }
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        if (mCommonTitleTv != null) {
            mCommonTitleTv.setText(resId);
        } else {
            mCommonToolbar.setTitle(resId);
        }
    }

    @Override
    public void invalidToken() {
        //用于检测你当前用户的token是否有效，无效就返回登录界面，具体的业务逻辑你自己实现
        //如果需要做到实时检测，推荐用socket长连接，每隔10秒发送一个验证当前登录用户token是否过期的请求
    }

    /**
     * Toast 提示用户
     *
     * @param msg 提示内容String
     */
    @Override
    public void showMsg(String msg) {
        MsgToast.show(this, msg);
    }

    /**
     * Toast 提示用户
     * @param resId resId
     */
    @Override
    public void showMsg(int resId) {
        MsgToast.show(this, getString(resId));
    }

    /**
     * 网络请求的时候显示正在加载的对话框
     */
    @Override
    public void showLoading() {
        if (null == mDialog) {
            mDialog = new AlertDialog.Builder(this).setView(new ProgressBar(this)).create();
            mDialog.setCanceledOnTouchOutside(false);
            Window window = mDialog.getWindow();
            if (null != window) {
                window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        }
        if (!mDialog.isShowing()) {
            mDialog.show();
        }
    }

    /**
     * 网络请求完成时隐藏加载对话框
     */
    public void hideLoading() {
        if (null != mDialog) {
            if (mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mDialog = null;
        }
    }

    /**
     * Finish当前页面
     */
    @Override
    public void customFinish() {
        onBackPressed();
    }

}
