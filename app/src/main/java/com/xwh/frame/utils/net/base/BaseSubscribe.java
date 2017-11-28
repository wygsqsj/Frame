package com.xwh.frame.utils.net.base;

import com.xwh.frame.utils.net.config.ExceptionHandler;
import com.xwh.frame.utils.net.config.HttpConstants;

import rx.Subscriber;

/**
 * 订阅者基类
 * Created by xwh on 2017/11/14.
 */
public abstract class BaseSubscribe<T> extends Subscriber<T> {

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof Exception) {
            //访问获得对应的Exception
            _onError(ExceptionHandler.handleException(e));
        } else {
            //将Throwable 和 未知错误的status code返回
            _onError(new ExceptionHandler.ResponseThrowable(e, HttpConstants.NET_UNKNOWN_ERROR, "未知错误！"));
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    protected abstract void _onError(Throwable e);

    protected abstract void _onNext(T t);
}
