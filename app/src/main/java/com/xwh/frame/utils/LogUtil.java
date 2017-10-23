package com.xwh.frame.utils;

import android.util.Log;

/**
 * Created by xwh on 2017/10/18.
 * <p>
 * Log日志管理类，在Application中初始化
 * LOG_LEVEL 为配置项
 * 小于2为最大权限 大于7便相当于关闭Log
 */
public class LogUtil {

    //默认log权限最大
    public static int LOG_LEVEL = 0;

    public static void v(String msg) {
        if (Log.VERBOSE > LOG_LEVEL)
            Log.v(getClassName(), msg);
    }

    public static void v(String tag, String msg) {
        if (Log.VERBOSE > LOG_LEVEL)
            Log.v(tag, msg);

    }

    public static void d(String msg) {
        if (Log.DEBUG > LOG_LEVEL)
            Log.d(getClassName(), msg);
    }

    public static void d(String tag, String msg) {
        if (Log.DEBUG > LOG_LEVEL)
            Log.d(tag, msg);

    }

    public static void i(String msg) {
        if (Log.INFO > LOG_LEVEL)
            Log.i(getClassName(), msg);
    }

    public static void i(String tag, String msg) {
        if (Log.INFO > LOG_LEVEL)
            Log.i(tag, msg);
    }

    public static void w(String msg) {
        if (Log.WARN > LOG_LEVEL)
            Log.w(getClassName(), msg);
    }

    public static void w(String tag, String msg) {
        if (Log.WARN > LOG_LEVEL)
            Log.w(tag, msg);
    }

    public static void e(String msg) {
        if (Log.ERROR > LOG_LEVEL)
            Log.e(getClassName(), msg);
    }

    public static void e(String tag, String msg) {
        if (Log.ERROR > LOG_LEVEL)
            Log.e(tag, msg);
    }

    private static String getClassName() {
        String result;
        StackTraceElement thisMethodStack = (new Exception()).getStackTrace()[2];
        String[] array = thisMethodStack.getClassName().split("\\.");
        result = array[array.length - 1];
        return result;
    }

}
