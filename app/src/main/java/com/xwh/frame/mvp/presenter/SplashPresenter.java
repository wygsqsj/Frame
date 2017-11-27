package com.xwh.frame.mvp.presenter;

import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.impl.UserImpl;
import com.xwh.frame.mvp.view.ISplashView;

/**
 * Created by xwh on 2017/10/19.
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    private UserImpl mModel;

    @Override
    protected void initModel() {
        mModel = new UserImpl(lifecycleSubject);
    }


}
