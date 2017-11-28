package com.xwh.frame.utils.net.config;

import com.google.gson.JsonParseException;
import com.xwh.frame.app.App;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.NetConnectUtil;
import com.xwh.frame.utils.ToastUtil;

import org.json.JSONException;

import java.net.ConnectException;
import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by xwh on 2017/11/14.
 * retrofit统一的异常处理类
 */

public class ExceptionHandler {

    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    /**
     * 将网络错误HttpException转换为我们自定义的ResponeThrowable
     *
     * @param e
     * @return
     */
    public static ResponseThrowable handleException(Throwable e) {
        LogUtil.i(e.toString());
        ResponseThrowable ex;
        String message = "网络连接出错，请稍后再试!";
        if (e instanceof UnknownHostException) {
            if (null == NetConnectUtil.getCurrentNetworkState(App.getContext())) {
                message = "当前网络不可用，请检查您的网络连接！";
            }
            ex = new ResponseThrowable(e, HttpConstants.NET_HTTP_ERROR, message);
            return ex;
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                    message = "请求地址异常，请稍后重试！";
                    break;
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                    message = "服务器异常，请稍后重试！";
                    break;
            }
            ex = new ResponseThrowable(e, HttpConstants.NET_HTTP_ERROR, message);
            return ex;
        } else if (e instanceof ResultException) {
            ResultException resultException = (ResultException) e;
            switch (resultException.getErrorCode()) {
                case HttpConstants.RESULT_UN_LOGIN://未登录或超时
                    break;

                case HttpConstants.NET_RESULT_JSON_FAIL://解析失败
                    break;
            }
            ex = new ResponseThrowable(resultException, resultException.getErrorCode(), resultException.getMessage());
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException) {
            ex = new ResponseThrowable(e, HttpConstants.NET_RESULT_JSON_FAIL, "数据解析错误");
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ResponseThrowable(e, HttpConstants.NET_RESULT_NET_FAIL, "网络连接失败");
            return ex;
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            ex = new ResponseThrowable(e, HttpConstants.NET_SSL_ERROR, "网络证书验证失败");
            return ex;
        } else {
            ex = new ResponseThrowable(e, HttpConstants.NET_UNKNOWN_ERROR, "发生了未知的网络异常，请稍后重试！");
            return ex;
        }
    }

    public static class ResponseThrowable extends Throwable {
        private String code;
        private String message;

        public ResponseThrowable(Throwable throwable, String code, String message) {
            super(throwable);
            this.code = code;
            this.message = message;
            ToastUtil.showShort(App.getContext(), message);
        }
    }
}
