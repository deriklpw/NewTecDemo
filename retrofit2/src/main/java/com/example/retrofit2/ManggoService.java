package com.example.retrofit2;

import com.example.retrofit2.bean.MangGuoBean;
import com.example.retrofit2.bean.WeatherBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by Derik on 2018/10/22.
 * Email: weilai0314@163.com
 */

public interface ManggoService {

    @GET("cx?")
    Observable<MangGuoBean> getAdApi(@QueryMap Map<String, String> params);

    @POST("app/player?")
    Observable<WeatherBean> getWeather(@QueryMap Map<String, String> params);
}
