package com.example.retrofit2;

import android.graphics.Color;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends BaseActivity {

    private static final String TAG = MainActivity.class.getName();
    private String[] mTargetNames = new String[]{
            "get",
            "post"
    };

    private RecycleViewAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.activity_main);
        setTitle("Retrofit2.0使用示例");
        setBackArrow();
        initViews();
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
                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://gank.io/")
                                .build();
                        Api api = retrofit.create(Api.class);
                        Call<ResponseBody> call = api.getAndroidInfo();
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                try {
                                    String result = response.body().string();
                                    Logger.d(result);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });
                        break;
                    case 1:
                        Retrofit retrofit2 = new Retrofit.Builder()
                                .baseUrl("http://v.juhe.cn/")
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
                        Api api2 = retrofit2.create(Api.class);
                        Map<String, String> params = new HashMap<>();
                        params.put("format","1");
                        params.put("cityname", "上海");
                        params.put("key", "8b6f1ed54dfaf42cc7b89644afdfff2e");

                        //异步
                        api2.getWeather(params).enqueue(new Callback<WeatherBean>() {
                            @Override
                            public void onResponse(Call<WeatherBean> call, Response<WeatherBean> response) {
                                String info = response.body().getResult().getToday().getWeather();
                                Logger.d("上海天气：" + info);
                            }

                            @Override
                            public void onFailure(Call<WeatherBean> call, Throwable t) {

                            }
                        });
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
