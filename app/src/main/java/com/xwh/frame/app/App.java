package com.xwh.frame.app;

import android.app.Application;

import com.xwh.frame.utils.LogUtil;

/**
 * Created by xwh on 2017/10/19.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initConfig();
    }


    private void initConfig() {
        //Log 开关
        LogUtil.LOG_LEVEL = 0;
    }


}
