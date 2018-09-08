package com.example.okhttp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.common.activity.BaseActivity;
import com.example.common.adapter.ItemDividerHorizontal;
import com.example.common.adapter.RecycleViewAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();
    private String[] mTargetNames = new String[]{
            "get"
    };

    private RecycleViewAdapter mAdapter;

    private OkHttpClientUtils mOkHttpClientUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        setTitle("OKHttp使用示例");
        setBackArrow();

        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestPermission(Manifest.permission.INTERNET, 0);
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        // 线性排列
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        // 设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDividerHorizontal().setDividerColor(Color.GRAY).setDividerSize(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        mAdapter = new RecycleViewAdapter(mTargetNames, null);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        if (mOkHttpClientUtils != null) {
                            mOkHttpClientUtils.get("https://gank.io/api/data/Android/10/1", new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Logger.d(call.toString());
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    Logger.d(response.body().string());
                                }
                            });
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void requestPermission(String permission, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    Logger.d("需要获取权限：" + permission);
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{permission}, requestCode);
                }

            } else {
                if (mOkHttpClientUtils == null) {
                    mOkHttpClientUtils = OkHttpClientUtils.getClient();
                }
                Logger.d("已经有权限：" + permission);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    mOkHttpClientUtils = OkHttpClientUtils.getClient();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    mOkHttpClientUtils = null;
                }
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                break;
        }
    }

}
