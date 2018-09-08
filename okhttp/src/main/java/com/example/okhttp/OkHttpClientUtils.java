package com.example.okhttp;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by derik on 18-8-25
 * <p>
 * Email: weilai0314@163.com
 */
public class OkHttpClientUtils {
    private static OkHttpClient client;

    private OkHttpClientUtils() {
        client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .build();
    }

    public static OkHttpClientUtils getClient() {
        return new OkHttpClientUtils();
    }

    public void get(String url, Callback callback) {
        client.newCall(new Request.Builder().url(url).build()).enqueue(callback);
    }


    public void postFormBody(String url, FormBody formBody, Callback callback) {
        client.newCall(new Request.Builder().url(url).post(formBody).build()).enqueue(callback);
    }

    public void postRequsetBody(String url, RequestBody requestBody, Callback callback) {
        client.newCall(new Request.Builder().url(url).post(requestBody).build()).enqueue(callback);
    }

    public void postJson(String url, String json, Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        client.newCall(new Request.Builder().url(url).post(requestBody).build()).enqueue(callback);
    }

    public void postFile(String url, File file, Callback callback){
        RequestBody requestBody = RequestBody.create(MediaType.parse("file/*"), file);
        client.newCall(new Request.Builder().url(url).post(requestBody).build()).enqueue(callback);
    }

}