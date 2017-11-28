package com.xwh.frame.mvp.presenter;

import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.bean.UpDateApk;
import com.xwh.frame.mvp.model.impl.UserImpl;
import com.xwh.frame.mvp.view.IMainView;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.net.subscribe.CommonSubscribe;

/**
 *
 * Created by xwh on 2017/10/18.
 */
public class MainPresenter extends BasePresenter<IMainView> {

    private UserImpl userImpl;

    @Override
    protected void initModel() {
        userImpl = new UserImpl(lifecycleSubject);
    }


    public void checkUpdate() {
        userImpl.checkUpdate(new CommonSubscribe<UpDateApk>() {
            @Override
            protected void _onError(Throwable e) {
                LogUtil.i("检查APK更新出错！" + e.toString());
            }

            @Override
           protected void _onNext(UpDateApk upDateApk) {
                LogUtil.i("成功"+upDateApk.toString());
                view.checkUpdateSucceed(upDateApk);
            }
        });
    }
}
