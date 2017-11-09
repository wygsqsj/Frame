package com.xwh.frame.mvp.presenter;

import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.bean.Joke;
import com.xwh.frame.mvp.view.ISplashView;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.net.BeanRequest;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by xwh on 2017/10/19.
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    @Override
    protected void initModel() {
    }

    public void checkApk() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "75f8d152fbb1d1618a0f9a1fa5bbdb34");
        map.put("page", "2");
        map.put("sort", "desc");
        map.put("pagesize", "10");
        map.put("time", "1418816972");

        new BeanRequest<Joke>(lifecycleSubject, Joke.class)
                .get("joke/content/list.from",
                        map,
                        new Subscriber<Joke>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                LogUtil.i("失败" + e.toString());
                            }

                            @Override
                            public void onNext(Joke joke) {
                                LogUtil.i("成功" + joke.toString());

                            }
                        });

    }
}
