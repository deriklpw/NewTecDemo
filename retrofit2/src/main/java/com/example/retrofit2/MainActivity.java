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
import com.example.retrofit2.bean.MangGuoBean;
import com.example.retrofit2.bean.WeatherBean;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
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
            "post",
            "Retrofit, Rxjava",
            "MangGuo Get",
            "MangGuo Post"
    };

    private RecycleViewAdapter mAdapter;
    private WeatherService weatherService;
    private ManggoService manggoService;
    private MangGuoBean.ParamBean.PBean pBean;
    private MangGuoBean.ParamBean.VBeanX vBean;
    private String path;

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
                                .client(OkHttpClientUtils.getClient())
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
                    case 2:
                        if (weatherService == null) {
                            weatherService = RetrofitServiceManager.getInstance().create(BaseUrl.WEATHER_URL, WeatherService.class);
                        }
                        Map<String, String> params1 = new HashMap<>();
                        params1.put("format","1");
                        params1.put("cityname", "上海");
                        params1.put("key", "8b6f1ed54dfaf42cc7b89644afdfff2e");

                        weatherService.getWeather(params1)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new DisposableObserver<WeatherBean>() {
                                    @Override
                                    public void onNext(WeatherBean weatherBean) {
                                        String info = weatherBean.getResult().getToday().getWeather();
                                        Logger.d("onNext:  上海天气：" + info);
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Logger.d("onError");
                                    }

                                    @Override
                                    public void onComplete() {
                                        Logger.d("onComplete");
                                    }
                                });

                        break;

                    case 3:
                        if (manggoService == null) {
                            manggoService = RetrofitServiceManager.getInstance().create(BaseUrl.MANG_GUO_URL, ManggoService.class);
                        }

                        Map<String, String> manggo_params = new HashMap<>();
                        manggo_params.put("cxid","6778_zm_0");
                        manggo_params.put("plan", "201847");
                        manggo_params.put("ip", "119.137.55.195");
                        manggo_params.put("platform", "phone");

                        manggoService.getAdApi(manggo_params)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(new DisposableObserver<MangGuoBean>() {
                                    @Override
                                    public void onNext(MangGuoBean mangGuoBean) {
                                        path = mangGuoBean.getPath();
                                        Logger.d("onNext: "+ path);
                                        pBean = mangGuoBean.getParam().getP();
                                        vBean = mangGuoBean.getParam().getV();
                                    }

                                    @Override
                                    public void onError(Throwable e) {
                                        Logger.d("onError:" + e.toString());
                                    }

                                    @Override
                                    public void onComplete() {
                                        Logger.d("onComplete");
                                    }
                                });
                        break;
                    case 4:
                        Gson gson = new Gson();
                        OkHttpClientUtils okHttpClientUtils = OkHttpClientUtils.getInstance();
                        String jsonPBean = gson.toJson(pBean);
                        String jsonVBean = gson.toJson(vBean);
                        Logger.d("p=" + jsonPBean);
                        Logger.d("v=" + jsonVBean);
                        FormBody formBody = new FormBody.Builder()
                                .add("p",jsonPBean)
                                .add("v",jsonVBean)
                                .build();

                        okHttpClientUtils.postFormBody(path, formBody , new okhttp3.Callback() {
                            @Override
                            public void onFailure(okhttp3.Call call, IOException e) {
                                Logger.d("onFailure");
                            }

                            @Override
                            public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                                Logger.d("response="+response.body().string());

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
