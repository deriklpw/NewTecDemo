package com.example.derik.newtecdemo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import com.example.derik.newtecdemo.R;
import com.example.derik.newtecdemo.adapter.ItemDividerHorizontal;
import com.example.derik.newtecdemo.adapter.RecycleViewAdapter;
import com.example.derik.newtecdemo.utils.OkHttpClientUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OkHttpTestActivity extends BaseActivity {

    private static final String TAG = "OkHttpTestActivity";
    private Intent mTargetIntent;
    private String[] mTargetNames = new String[]{
            "get",
            "post"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_ok_http_test);//设置到BaseActivity中的content中
        setTitle("OKHttp使用示例");//设置标题
        setBackArrow();//设置返回按钮和点击事件
        initViews();
    }

    private void initViews() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);

        // 线性排列
        LinearLayoutManager layoutManager = new LinearLayoutManager(getBaseContext());
        // 设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new ItemDividerHorizontal().setDividerColor(Color.GRAY).setmDividerSize(2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RecycleViewAdapter adapter = new RecycleViewAdapter(mTargetNames, null);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecycleViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                switch (position) {
                    case 0:
                        OkHttpClientUtils.getClient().get("https://www.baidu.com", new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Logger.d(call.toString());
                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                Logger.d(response.body().string());
                            }
                        });

                        break;
                    case 1:
                        String json = "{\"username\": \"derik\", \"password\": \"123456\"}";
                        OkHttpClientUtils.getClient().postJson("https://www.baidu.com", json
                                , new Callback() {
                                    @Override
                                    public void onFailure(Call call, IOException e) {
                                        Logger.d(call.toString());
                                    }

                                    @Override
                                    public void onResponse(Call call, Response response) throws IOException {
                                        Logger.d(response.body().string());
                                    }
                                });
                        break;
                    case 2:
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
