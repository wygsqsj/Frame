package com.xwh.frame.utils.net;

import com.xwh.frame.app.App;
import com.xwh.frame.utils.ToastUtil;

/**
 * 处理网络请求错误码
 * Created by xwh on 2017/11/6.
 */

public class ResultException extends RuntimeException {

    private final String errorMessage;

    public ResultException(String errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        handlerError(errorCode);
    }

    private void handlerError(String errorCode) {
        switch (errorCode) {
            case HttpConstans.RESULT_UN_LOGIN://未登录或超时
                break;

            case HttpConstans.RESULT_JSON_FAIL://解析失败
                break;
            default:
                ToastUtil.showShort(App.getContext(), errorMessage);
                break;
        }
    }

    @Override
    public String toString() {
        return errorMessage;
    }

    @Override
    public String getMessage() {
        return errorMessage;
    }
}
