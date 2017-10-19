package com.xwh.frame.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by xwh on 2017/10/19.
 * <p/>
 * retrofit的配置类
 */
public class RetrofitConfig {

    //添加okhttp配置
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(5, TimeUnit.SECONDS)
            .writeTimeout(5, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .retryOnConnectionFailure(true)
            .build();

}
