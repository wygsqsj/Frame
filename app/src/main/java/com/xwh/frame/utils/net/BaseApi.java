package com.xwh.frame.utils.net;

import com.xwh.frame.app.App;
import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.utils.LogUtil;

import rx.Observable;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

/**
 * 网络请求的封装类
 * 将publishSubject接收到这里，并以此构建出一个Observable用于子类添加到数据链中进行判断
 * <p>
 * Created by xwh on 2017/11/9.
 */

abstract class BaseApi {

    /**
     * 默认生命周期为destory，当页面Destory之后取消数据传递
     */
    private BasePresenter.LifeCycleEvent lifeCycleEventFlag = BasePresenter.LifeCycleEvent.DESTROY;
    protected Service service;
    Observable<BasePresenter.LifeCycleEvent> compareLifecycleObservable;

    BaseApi(PublishSubject<BasePresenter.LifeCycleEvent> lifecycleSubject) {
        service = App.retrofit.create(Service.class);
        compareLifecycleObservable =
                lifecycleSubject.takeFirst(new Func1<BasePresenter.LifeCycleEvent, Boolean>() {
                    @Override
                    public Boolean call(BasePresenter.LifeCycleEvent lifeCycleEvent) {
                        LogUtil.i("收到界面生命周期改变");
                        //当界面销毁之后断开数据传递
                        return lifeCycleEvent.equals(lifeCycleEventFlag);
                    }
                });
    }
}