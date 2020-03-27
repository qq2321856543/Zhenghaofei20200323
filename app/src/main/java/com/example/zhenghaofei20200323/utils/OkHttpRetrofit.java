package com.example.zhenghaofei20200323.utils;

import android.text.TextUtils;

import com.example.zhenghaofei20200323.base.Apis;
import com.example.zhenghaofei20200323.base.App;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class OkHttpRetrofit  {

    private Apis apis;

    private OkHttpRetrofit(){
        init();
    }
    private static class getHttp{
        private static final OkHttpRetrofit OK_HTTP_RETROFIT=new OkHttpRetrofit();
    }
    public static OkHttpRetrofit getInstance(){
        return getHttp.OK_HTTP_RETROFIT;
    }
    public void init(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                //.addInterceptor(new Header())
                .addInterceptor(httpLoggingInterceptor);
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://mobile.bwstudent.com/small/")
                .client(builder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apis = build.create(Apis.class);
    }
    public Apis getApis(){
        return apis;
    }
    public class Header implements Interceptor{

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
//            String userId = SPUtil.getString(App.getContext(), "userId");
//            String sessionId = SPUtil.getString(App.getContext(), "sessionId");
//            if (TextUtils.isEmpty(userId)||TextUtils.isEmpty(sessionId)){
//                return chain.proceed(request);
//            }
            Request build = request.newBuilder()
                    .addHeader("userId", "33324")
                    .addHeader("sessionId", "158527438971233324")
                    .build();
            return chain.proceed(build);
        }
    }
}
