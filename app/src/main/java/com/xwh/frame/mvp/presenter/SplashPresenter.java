package com.xwh.frame.mvp.presenter;

import com.xwh.frame.app.App;
import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.SplashModel;
import com.xwh.frame.mvp.model.bean.BeforeToday;
import com.xwh.frame.mvp.view.ISplashView;
import com.xwh.frame.net.RequestImpl;
import com.xwh.frame.net.Service;
import com.xwh.frame.utils.LogUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by xwh on 2017/10/19.
 */
public class SplashPresenter extends BasePresenter<ISplashView> {

    private SplashModel model;
    private RequestImpl mRequest;

    @Override
    protected void initModel() {
        model = new SplashModel();
        mRequest = new RequestImpl<BeforeToday>(BeforeToday.class);
    }

    public void checkApk() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "be6da3836eb321422a21d00beda5a542");
        map.put("v", "1");
        map.put("month", "10");
        map.put("day", "24");

        App.retrofit.create(Service.class)
                .post(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("失败" + e.toString());
                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.i("成功" + s);
                    }
                });

        /*mRequest.post(map)
                .subscribe(new Subscriber<BeforeToday>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtil.i("失败" + e.toString());
                    }

                    @Override
                    public void onNext(BeforeToday beforeToday) {
                        LogUtil.i("成功" + beforeToday.toString());
                    }
                });*/


    }
}
