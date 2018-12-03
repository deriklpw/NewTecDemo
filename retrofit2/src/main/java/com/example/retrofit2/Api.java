package com.example.retrofit2;

import com.example.retrofit2.bean.WeatherBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface Api {
    @GET("api/data/Android/10/1")
    Call<ResponseBody> getAndroidInfo();

    @POST("weather/index?")
    Call<WeatherBean> getWeather(@QueryMap Map<String , String> params);
}
