package com.xwh.frame.base;

/**
 * Created by xwh on 2017/10/18.
 */
public abstract class BasePresenter<V extends IBaseView> {

    public V view;

    public void attach(V view) {
        this.view = view;
        initModel();
    }

    /**
     * 在这里new出对应的model
     */
    protected abstract void initModel();

    /**
     * 取消和view层的关联
     */
    public void detach() {
        this.view = null;
    }
}
