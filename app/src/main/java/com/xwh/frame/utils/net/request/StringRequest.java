package com.xwh.frame.utils.net.request;

import com.xwh.frame.base.BasePresenter;
import com.xwh.frame.mvp.model.bean.ResponseBean;
import com.xwh.frame.utils.net.base.BaseApi;
import com.xwh.frame.utils.net.config.HttpConstants;
import com.xwh.frame.utils.net.config.ResultException;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * 网络请求工具
 * 返回ResponseBean中的data数据，为Sting类型
 * 是为了解决data中数据较少，无需构建一个bean来存储的情况
 * <p>
 * Created by xwh on 2017/11/9.
 */

public class StringRequest extends BaseApi {

    StringRequest(PublishSubject<BasePresenter.LifeCycleEvent> lifecycleSubject) {
        super(lifecycleSubject);
    }

    public void post(Map<String, String> questMap, Subscriber<String> subscriber) {
        service.post(questMap)
                .compose(new HttpStringTransformor())
                .subscribe(subscriber);
    }


    public void get(String path, Map<String, String> questMap, Subscriber<String> subscriber) {
        service.get(path, questMap)
                .compose(new HttpStringTransformor())
                .subscribe(subscriber);
    }


    /**
     * 用于返回String类型的Observerble转换器
     */
    private class HttpStringTransformor
            implements Observable.Transformer<ResponseBean, String> {
        @Override
        public Observable<String> call(Observable<ResponseBean> responseBeanObservable) {
            return responseBeanObservable
                    .map(new Func1<ResponseBean, String>() {
                        @Override
                        public String call(ResponseBean responseBean) {
                            String data;
                            String resultCode = responseBean.getError_code();
                            if (HttpConstants.RESULT_SUCCESS.equals(resultCode)) {  //获取到数据
                                data = responseBean.getResult().toString();
                                return data;
                            } else {
                                throw new ResultException(resultCode, responseBean.getReason());
                            }
                        }
                    })
                    .takeUntil(compareLifecycleObservable)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    }
}
