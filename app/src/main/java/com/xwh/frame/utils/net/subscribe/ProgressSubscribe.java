package com.xwh.frame.utils.net.subscribe;


import com.xwh.frame.base.IBaseView;
import com.xwh.frame.utils.net.base.BaseSubscribe;

/**
 * 观察者，用于处理Dialog的显示和取消
 * 如果需要自定义界面的dialog请求，直接使用rx包中的观察者就可以
 * <p>
 * Created by xwh on 2017/11/6.
 */
public abstract class ProgressSubscribe<T> extends BaseSubscribe<T> {

    private IBaseView view;

    @Override
    public void onStart() {
        super.onStart();
        view.showProgressDialog();
    }

    public ProgressSubscribe(IBaseView view) {
        this.view = view;
    }

    @Override
    public void onCompleted() {
        view.hideProgressDialog();
    }

    @Override
    public void onError(Throwable e) {
        super.onError(e);
        view.immedHideProDialog();
    }


    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    protected abstract void _onError(Throwable e);

    protected abstract void _onNext(T t);
}
