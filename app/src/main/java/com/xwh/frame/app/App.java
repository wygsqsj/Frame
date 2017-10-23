package com.xwh.frame.app;

import android.app.Application;
import android.content.Context;

import com.xwh.frame.net.RetrofitConfig;
import com.xwh.frame.utils.LogUtil;

import retrofit2.Retrofit;

/**
 * Created by xwh on 2017/10/19.
 */
public class App extends Application {

    public static Retrofit retrofit;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        initConfig();
    }


    private void initConfig() {
        //Log 开关
        LogUtil.LOG_LEVEL = 0;
        retrofit = RetrofitConfig.getInstance().getRetrofit();
    }

    /**
     * 获取App的上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

}
