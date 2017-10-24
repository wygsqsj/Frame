package com.xwh.frame.net;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by xwh on 2017/5/9.
 */
public interface Service {

    /**
     * 项目中统一的Post请求格式
     * 由于不同的项目请求参数不同在此只写map类型的请求参数
     * 返回数据一般都是Response类中所写的形式，所以统一封装一下
     *
     * @return Observable<Response>
     */
    @FormUrlEncoded
    @POST("japi/toh")
    Observable<String> post(@FieldMap Map<String, String> requestMap);

}
