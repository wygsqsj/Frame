package com.xwh.frame.utils.net;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by xwh on 2017/10/19.
 * <p>
 * retrofit的配置类
 */
public class RetrofitConfig {

    private Retrofit retrofit;

    private static RetrofitConfig retrofitConfig;

    private RetrofitConfig() {
        retrofitBuilder();
    }

    /**
     * 更改baseUrl的拦截器
     * 当app与okHtpp链接时判断请求的接口Headers里面是否含有
     * 标志，如果有更改url后重新builder
     */
    private Interceptor urlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            //获取request
            Request request = chain.request();
            //获取request的创建者builder
            Request.Builder builder = request.newBuilder();
            //从request中获取headers，通过给定的键url_name
            List<String> headerValues = request.headers("checkApk");
            if (headerValues != null && headerValues.size() > 0) {
                //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
                builder.removeHeader("checkApk");

                //匹配获得新的BaseUrl
                String headerValue = headerValues.get(0);
                HttpUrl newBaseUrl = null;
                if ("0".equals(headerValue)) {
//                    newBaseUrl = HttpUrl.parse(HttpConstans.OTHER_URL);
                } else {
                    newBaseUrl = HttpUrl.parse(HttpConstans.BASE_URL);
                }
                //从request中获取原有的HttpUrl实例oldHttpUrl
                HttpUrl oldHttpUrl = request.url();
                //重建新的HttpUrl，修改需要修改的url部分
                HttpUrl newFullUrl = oldHttpUrl
                        .newBuilder()
                        .scheme(newBaseUrl.scheme())
                        .host(newBaseUrl.host())
                        .port(newBaseUrl.port())
                        .build();

                //重建这个request，通过builder.url(newFullUrl).build()；
                //然后返回一个response至此结束修改
                return chain.proceed(builder.url(newFullUrl).build());
            } else {
                return chain.proceed(request);
            }
        }
    };

    public static RetrofitConfig getInstance() {
        if (retrofitConfig == null) {
            synchronized (RetrofitConfig.class) {
                if (retrofitConfig == null) {
                    retrofitConfig = new RetrofitConfig();
                }
            }
        }
        return retrofitConfig;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    private void retrofitBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConstans.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//添加json数据转换器，可自己定义
//                .addConverterFactory(ScalarsConverterFactory.create())
                .client(getOkHttpClent())
                .build();

    }

    private OkHttpClient getOkHttpClent() {
        //添加okhttp配置
        return new OkHttpClient.Builder()
                .connectTimeout(HttpConstans.connectTimeoutMills, TimeUnit.SECONDS)
                .readTimeout(HttpConstans.readTimeoutMills, TimeUnit.SECONDS)
                .writeTimeout(HttpConstans.writeTimeoutMills, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)//出现错误后是否重连
                .build();
    }


}
