package com.xwh.frame.net;

/**
 * Created by xwh on 2017/10/20.
 * 网络请求配置类
 */
public class HttpConstans {

//    public static final String BASE_URL = "https://test.crfchina.com/";

    public static final String BASE_URL = "http://api.juheapi.com/";


    public static final long connectTimeoutMills = 8 * 1000L;

    public static final long readTimeoutMills = 8 * 1000L;

    public static final long writeTimeoutMills = 8 * 1000L;

    /**
     * 表示正确处理请求
     */
    public static final String RESULT_SUCCESS = "0000";
    /**
     * 表示用于未登录或者登录超时
     */
    public static final String RESULT_UN_LOGIN = "8888";
    /**
     * 表示后台有返回的提示信息(无信息表示后台出现异常)
     */
    public static final String RESULT_SERVICE_WRONG = "9999";
    /**
     * 解析失败
     */
    public static final String RESULT_JSON_FAIL = "101";
    /**
     * 网络连接失败
     */
    public static final String RESULT_NET_FAIL = "102";
}
