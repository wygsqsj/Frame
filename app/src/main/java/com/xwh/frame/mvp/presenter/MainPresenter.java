package com.xwh.frame.mvp.presenter;

import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.bean.Joke;
import com.xwh.frame.mvp.model.impl.UserImpl;
import com.xwh.frame.mvp.view.IMainView;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.net.subscribe.ProgressSubscribe;

/**
 * Created by xwh on 2017/10/18.
 */
public class MainPresenter extends BasePresenter<IMainView> {

    private UserImpl mModel;

    @Override
    protected void initModel() {
        mModel = new UserImpl(lifecycleSubject);
    }

    public void load() {
        mModel.load(new ProgressSubscribe<Joke>(view) {
            @Override
            protected void _onError(Throwable e) {
                LogUtil.i("错误" + e.toString());
            }

            @Override
            protected void _onNext(Joke joke) {
                LogUtil.i("正确" + joke.toString());
            }
        });
    }
}
