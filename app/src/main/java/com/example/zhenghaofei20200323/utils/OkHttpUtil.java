package com.example.zhenghaofei20200323.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
/**
 * Zhenghaofei20200323
 * 工具类
 * 2020-03-23
 */
public class OkHttpUtil {
    Handler handler;
    private OkHttpClient mClient;

    private OkHttpUtil(){
        setOkHttp();
    }
    private static class getOk{
        private static final OkHttpUtil OK_HTTP_UTIL=new OkHttpUtil();
    }
    public static OkHttpUtil getInstance(){
        return getOk.OK_HTTP_UTIL;
    }
    public void setOkHttp(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        handler = new Handler();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(httpLoggingInterceptor);
        mClient = builder.build();
    }
    public Boolean isWifi(Context context){
        ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info!=null){
            return true;
        }
        return false;
    }
    public void doget(String url, Map<String,String> header, final Callback callback){
        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String,String> entry:header.entrySet()){
            builder.addHeader(entry.getKey(),entry.getValue());
        }
        Request build = builder.url(url).build();
        mClient.newCall(build).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.getSuccess(string);
                        }
                    });
                }
            }
        });
    }
    public void doPost(String url, Map<String,String> header, Map<String,String> map, final Callback callback){
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String,String> entry:map.entrySet()){
            formBody.add(entry.getKey(),entry.getValue());
        }
        Request.Builder builder = new Request.Builder();
        for (Map.Entry<String,String> entry:header.entrySet()){
            builder.addHeader(entry.getKey(),entry.getValue());
        }
        Request build = builder.url(url).post(formBody.build()).build();
        mClient.newCall(build).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String string = response.body().string();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.getSuccess(string);
                        }
                    });
                }
            }
        });

    }
    public interface Callback{
        void getSuccess(String str);
        void getErroy(String str);
    }
}
