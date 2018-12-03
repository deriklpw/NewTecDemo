package com.example.retrofit2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Derik on 2018/10/15.
 * Email: weilai0314@163.com
 */

public class RetrofitServiceManager {

    private static final int CONNECT_TIME_OUT = 15;//超时时间 5s
    private static final int WRITE_READ_TIME_OUT = 20;
    private Retrofit.Builder mRetrofitBuilder;
    private static RetrofitServiceManager INSTANCE = null;

    private static Retrofit retrofit;
    private static Gson mGson;

    private RetrofitServiceManager() {

        mRetrofitBuilder = new Retrofit.Builder()
                .client(httpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create());
        retrofit = mRetrofitBuilder.build();
    }

    public static RetrofitServiceManager getInstance() {
        if (INSTANCE == null) {
            synchronized (RetrofitServiceManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RetrofitServiceManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 可以根据BaseUrl，每次创建Retrofit，再创建service
     * 适合BaseUrl不一样，service中的Path使用的是相对路径的情况
     * @param baseUrl
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(String baseUrl, Class<T> service) {
        return mRetrofitBuilder.baseUrl(baseUrl).build().create(service);
    }

    /**
     * 只创建一个Retrofit，每次创建service
     * 不设置BaseUrl，service中的Path使用完整url
     * @param service
     * @param <T>
     * @return
     */
    public <T> T create(Class<T> service) {
        return retrofit.create(service);
    }

    private static OkHttpClient httpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(CONNECT_TIME_OUT, TimeUnit.SECONDS);//连接超时时间
        builder.writeTimeout(WRITE_READ_TIME_OUT, TimeUnit.SECONDS);//写操作 超时时间
        builder.readTimeout(WRITE_READ_TIME_OUT, TimeUnit.SECONDS);//读操作超时时间 // 添加公共参数拦截器
        builder.retryOnConnectionFailure(true);
//        HttpCommonInterceptor commonInterceptor = new HttpCommonInterceptor.Builder()
//                .addHeaderParams("paltform", "android")
//                .addHeaderParams("userToken", "1234343434dfdfd3434")
//                .addHeaderParams("userId", "123445").build();
//        builder.addInterceptor(commonInterceptor); // 创建Retrofit



        return builder.build();
    }

    public static Gson gson() {
        if (mGson == null) {
            synchronized (RetrofitServiceManager.class) {
                mGson = new GsonBuilder().setLenient().create();
            }
        }
        return mGson;
    }
}
