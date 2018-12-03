package com.example.retrofit2;

import com.example.retrofit2.bean.WeatherBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Derik on 2018/10/15.
 * Email: weilai0314@163.com
 */

public interface WeatherService {
    @POST("weather/index?")
    Observable<WeatherBean> getWeather(@QueryMap Map<String, String> params);
}
