package com.xwh.frame.mvp.presenter;

import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.SplashModel;
import com.xwh.frame.mvp.view.ISplashView;

/**
 * Created by xwh on 2017/10/19.
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    @Override
    protected void initModel() {
        SplashModel model = new SplashModel();

    }
}
