package com.xwh.frame.utils.net.config;

/**
 *
 * Created by xwh on 2017/11/28.
 */

public class HttpConstants {

    public static final String BASE_URL = "http://japi.juhe.cn/";

    public static final String UPDATEAPK_URL = "apkUpdate.do";

    public static final long connectTimeoutMills = 8 * 1000L;

    public static final long readTimeoutMills = 8 * 1000L;

    public static final long writeTimeoutMills = 8 * 1000L;

    /**
     * 表示正确处理请求
     */
    public static final String RESULT_SUCCESS = "0";
    /**
     * 表示用于未登录或者登录超时
     */
    public static final String RESULT_UN_LOGIN = "8888";
    /**
     * 表示后台有返回的提示信息(无信息表示后台出现异常)
     */
    public static final String RESULT_SERVICE_WRONG = "9999";
    /**
     * 网络连接失败
     */
    public static final String RESULT_NET_FAIL = "102";

    /**
     * http协议出错
     */
    public static final String NET_HTTP_ERROR = "100";
    /**
     * 解析失败
     */
    public static final String NET_RESULT_JSON_FAIL = "1001";
    /**
     * 网络连接失败
     */
    public static final String NET_RESULT_NET_FAIL = "1002";

    /**
     * 证书验证错误
     */
    public static final String NET_SSL_ERROR = "1003";
    /**
     * 写入网络下载文件出错
     */
    public static final String WRITE_FILE_ERROR = "1004";
    /**
     * 未知网络错误
     */
    public static final String NET_UNKNOWN_ERROR = "1005";
}
