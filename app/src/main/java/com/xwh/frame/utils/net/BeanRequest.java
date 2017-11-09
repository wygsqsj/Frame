package com.xwh.frame.utils.net;

import com.google.gson.Gson;
import com.xwh.frame.mvp.model.bean.ResponseBean;
import com.xwh.frame.utils.LogUtil;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * 网络请求工具类
 * 返回ResponseBean中的data数据，为对应的实体类型
 * 具体的类型由调用者在构建时传入对应的实体类和泛型所确定
 * <p>
 * Created by xwh on 2017/11/9.
 */

public class BeanRequest<T> extends BaseApi {

    private final Class beanClass;
    private Gson mGson;

    public BeanRequest(PublishSubject lifecycleSubject, Class beanClass) {
        super(lifecycleSubject);
        this.beanClass = beanClass;
        mGson = new Gson();
    }

    public void post(Map<String, String> params, Subscriber<T> subscriber) {
        service.post(params)
                .compose(new HttpTypeTransformor())
                .subscribe(subscriber);
    }

    public void post(String questString, Subscriber<T> subscriber) {
        service.post(questString)
                .compose(new HttpTypeTransformor())
                .subscribe(subscriber);
    }

    public void get(String path, Map<String, String> questMap, Subscriber<T> subscriber) {
        service.get(path, questMap)
                .compose(new HttpTypeTransformor())
                .subscribe(subscriber);
    }


    /**
     * 用于返回对应数据的Observerble转换器
     */
    private class HttpTypeTransformor
            implements Observable.Transformer<ResponseBean, T> {
        @Override
        public Observable<T> call(Observable<ResponseBean> responseBeanObservable) {
            return responseBeanObservable
                    .map(new Func1<ResponseBean, T>() {
                        @Override
                        public T call(ResponseBean responseBean) {
                            T data;
                            String resultCode = responseBean.getError_code();
                            if (HttpConstans.RESULT_SUCCESS.equals(resultCode)) {  //获取到数据
                                LogUtil.i("retrofit", "获取到Data数据");
                                data = (T) mGson.fromJson(responseBean.getResult().toString().trim(), beanClass);
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