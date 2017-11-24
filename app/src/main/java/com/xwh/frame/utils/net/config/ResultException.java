package com.xwh.frame.utils.net.config;


/**
 * 处理网络请求错误码
 * Created by xwh on 2017/11/6.
 */

public class ResultException extends RuntimeException {

    private final String errorMessage;
    private final String errorCode;

    public ResultException(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        return "ResultException{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorCode='" + errorCode + '\'' +
                '}';
    }
}
