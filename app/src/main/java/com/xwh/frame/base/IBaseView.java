package com.xwh.frame.base;

import com.xwh.frame.utils.net.config.ExceptionHandler;

/**
 * Created by xwh on 2017/10/18.
 */
public interface IBaseView {
    /**
     * 显示加载弹窗
     */
    void showProgressDialog();

    /**
     * 普通定时方式关闭加载弹窗
     */
    void hideProgressDialog();

    /**
     * 立即关闭加载弹窗
     */
    void immedHideProDialog();

    /**
     * 加载失败
     */
    void fail(ExceptionHandler.ResponseThrowable exception);
}
