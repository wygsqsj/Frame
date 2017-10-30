package com.xwh.frame.mvp.presenter;

import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.SplashModel;
import com.xwh.frame.mvp.model.bean.BeforeToday;
import com.xwh.frame.mvp.model.bean.Joke;
import com.xwh.frame.mvp.view.ISplashView;
import com.xwh.frame.net.RequestImpl;
import com.xwh.frame.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;

/**
 * Created by xwh on 2017/10/19.
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    private SplashModel model;
    private RequestImpl mRequest;

    @Override
    protected void initModel() {
        model = new SplashModel();
        mRequest = new RequestImpl<Joke>(Joke.class);
    }

    public void checkApk() {
      /*  Map<String, String> map = new HashMap<>();
        map.put("key", "75f8d152fbb1d1618a0f9a1fa5bbdb34");
        map.put("page", "2");
        map.put("sort", "desc");
        map.put("pagesize", "10");
        map.put("time", "1418816972");
        new RequestImpl<Joke>(Joke.class)
                .get(map)
                .subscribe(new Subscriber<Joke>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("错误" + e.toString());
                    }

                    @Override
                    public void onNext(Joke joke) {
                        LogUtil.i("成功" + joke.toString());

                    }
                });*/


        Map<String, String> map = new HashMap<>();
        map.put("key", "be6da3836eb321422a21d00beda5a542");
        map.put("v", "1");
        map.put("month", "10");
        map.put("day", "26");
        new RequestImpl<BeforeToday>(BeforeToday.class)
                .post(map)
                .subscribe(new Subscriber<BeforeToday>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("错误" + e.toString());
                    }

                    @Override
                    public void onNext(BeforeToday beforeToday) {
                        LogUtil.i("成功" + beforeToday.toString());
                    }
                });

    }
}
