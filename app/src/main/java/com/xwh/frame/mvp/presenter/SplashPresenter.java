package com.xwh.frame.mvp.presenter;

import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.SplashModel;
import com.xwh.frame.mvp.view.ISplashView;
import com.xwh.frame.net.HttpParams;

/**
 * Created by xwh on 2017/10/19.
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    private SplashModel model;

    @Override
    protected void initModel() {
        model = new SplashModel();

    }

    public void checkApk() {
        mParams = HttpParams.obtion().setMethod("openPic").build();
        model.updateApk();
    }
}
