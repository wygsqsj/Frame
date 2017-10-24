package com.xwh.frame.net;

import com.google.gson.Gson;
import com.xwh.frame.app.App;
import com.xwh.frame.mvp.model.bean.ResponseBean;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.ToastUtil;

import java.util.Map;

import retrofit2.Retrofit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 统一处理项目中的网络请求，返回相应的Bean
 * Created by xwh on 2017/5/9.
 */
public class RequestImpl<T> {
    /**
     * 需要返回的数据类型
     */
    private Class beanClass;

    private Retrofit retrofit;

    private Service service;
    private Gson mGson;


    public RequestImpl(Class beanClass) {
        this.beanClass = beanClass;
        retrofit = App.retrofit;
        service = retrofit.create(Service.class);
        mGson = new Gson();
    }

    /**
     * post统一的请求格式
     *
     * @param requestMap 请求的参数
     * @return Observable<T>
     */
    public Observable<T> post(Map<String, String> requestMap) {
        return service.post(requestMap)
                .map(new HttpResultFunc())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 用来统一处理Http的resultCode,并将返回数据的的Data部分剥离出来返回给Subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<ResponseBean, T> {
        @Override
        public T call(ResponseBean responseBean) {
            String resultCode = responseBean.getErrorCode();
            T data = null;
            if (HttpConstans.RESULT_SUCCESS.equals(resultCode)) {  //获取到数据
                LogUtil.i("retrofit", "获取到Data数据");
                String str = responseBean.getResult().toString().trim();
                data = (T) mGson.fromJson(responseBean.getResult().toString().trim(), beanClass);
            } else if (HttpConstans.RESULT_UN_LOGIN.equals(resultCode)) {   //未登录或者登陆超时
                LogUtil.i("retrofit", "未登录或超时");
                ToastUtil.showShort(App.getContext(), "未登录或超时");
            } else if (HttpConstans.RESULT_SERVICE_WRONG.equals(resultCode)) {//获取数据失败
                LogUtil.i("retrofit", "获取数据失败");
                ToastUtil.showShort(App.getContext(), responseBean.getReason());
            }
            return data;
        }
    }
}
