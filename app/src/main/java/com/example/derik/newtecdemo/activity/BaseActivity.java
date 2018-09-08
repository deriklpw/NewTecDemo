package com.example.derik.newtecdemo.activity;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.derik.newtecdemo.R;

public abstract class BaseActivity extends AppCompatActivity {

    /**
     * 通用的ToolBar标题
     */
    private TextView mCommonTitleTv;
    /**
     * 通用的ToolBar
     */
    private Toolbar mCommonTitleTb;
    /**
     * 内容区域
     */
    private RelativeLayout mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        initView();
        setSupportActionBar(mCommonTitleTb);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initView() {
        mCommonTitleTv = (TextView) findViewById(R.id.common_title_tv);
        mCommonTitleTb = (Toolbar) findViewById(R.id.common_title_tb);
        mContent = (RelativeLayout) findViewById(R.id.content);

        if (mCommonTitleTb == null) {
            throw new IllegalStateException(
                    "The subclass of ToolbarActivity must contain a toolbar.");
        }
    }


    /**
     * 子类调用，重新设置Toolbar
     *
     * @param layout
     */
    public void setToolBar(int layout) {
        hideToolBar();
        mCommonTitleTb = (Toolbar) mContent.findViewById(layout);
        setSupportActionBar(mCommonTitleTb);
        //设置actionBar的标题是否显示，对应ActionBar.DISPLAY_SHOW_TITLE。
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    /**
     * 隐藏ToolBar，通过setToolBar重新定制ToolBar
     */
    public void hideToolBar() {
        mCommonTitleTb.setVisibility(View.GONE);
        mCommonTitleTb = null;
    }

    /**
     * menu的点击事件
     *
     * @param onclick
     */
    public void setToolBarMenuOnclick(Toolbar.OnMenuItemClickListener onclick) {
        mCommonTitleTb.setOnMenuItemClickListener(onclick);
    }


    /**
     * 设置左上角back按钮
     */
    public void setBackArrow() {
        final Drawable upArrow = getResources().getDrawable(android.R.drawable.arrow_up_float);
        //给ToolBar设置左侧的图标
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        // 给左上角图标的左边加上一个返回的图标 。对应ActionBar.DISPLAY_HOME_AS_UP
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //设置返回按钮的点击事件
        mCommonTitleTb.setNavigationOnClickListener(new View.OnClickListener() {
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
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(layoutId, null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        mContent.addView(contentView, params);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            mCommonTitleTv.setText(title);
        }
    }

    /**
     * 设置标题
     *
     * @param resId
     */
    public void setTitle(int resId) {
        mCommonTitleTv.setText(resId);
    }
}
