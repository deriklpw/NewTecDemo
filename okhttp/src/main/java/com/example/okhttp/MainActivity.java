package com.example.okhttp;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.common.activity.BaseActivity;
import com.example.common.adapter.ItemDividerHorizontal;
import com.example.common.adapter.RecycleViewAdapter;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.UUID;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    //    private static final String url = "https://gank.io/api/data/Android/10/1";
    private static String url = "http://cx.da.mgtv.com/cx?";
    private String[] mTargetNames = new String[]{
            "get",
            "post"
    };

    private RecycleViewAdapter mAdapter;

    private OkHttpClientUtils mOkHttpClientUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        setTitle("OKHttp使用示例");
        setBackArrow();
        try {
            String ip = getLocalIpAddress(this);
            UUID uuid = UUID.randomUUID();
            Log.d(TAG, "onCreate: ip=" + ip + " , uuid=" + uuid.toString());

            url += "cxid=6778_zm_0&plan=201847&id=" + uuid.toString() + "&ip=" + "&platform=phone";
            Log.d(TAG, "onCreate: url=" + url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        initViews();
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermission(Manifest.permission.INTERNET, 0);
        } else {
            // TODO 小于23，直接执行
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
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
                            mOkHttpClientUtils.get(url, new Callback() {
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
                    case 1:

                    default:
                        break;
                }
            }
        });
    }

    private void requestPermission(final String permission, final int requestCode) {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), permission) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("需要获取" + permission)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
                                Logger.d("ok");
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Logger.d("cancel");
                            }
                        }).create();
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


    /**
     * 获取当前ip地址
     *
     * @param context
     * @return
     */
    public static String getLocalIpAddress(Context context) {
        try {

            WifiManager wifiManager = (WifiManager) context.getApplicationContext()
                    .getSystemService(Context.WIFI_SERVICE);
            assert wifiManager != null;
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            int i = wifiInfo.getIpAddress();
            return int2ip(i);
        } catch (Exception ex) {
            return " 获取IP出错!\n" + ex.getMessage();
        }

    }

    /**
     * 将ip的整数形式转换成ip形式
     *
     * @param ipInt
     * @return
     */
    public static String int2ip(int ipInt) {
        StringBuilder sb = new StringBuilder();
        sb.append(ipInt & 0xFF).append(".");
        sb.append((ipInt >> 8) & 0xFF).append(".");
        sb.append((ipInt >> 16) & 0xFF).append(".");
        sb.append((ipInt >> 24) & 0xFF);
        return sb.toString();
    }
}

