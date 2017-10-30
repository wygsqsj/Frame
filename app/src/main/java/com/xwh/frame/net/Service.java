package com.xwh.frame.net;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by xwh on 2017/5/9.
 */
public interface Service {

    /**
     * 项目中统一的Post请求格式
     *
     * @return Observable<ResponseBean>
     */
    @FormUrlEncoded
    @POST("japi/toh")
    Observable<String> post(@FieldMap Map<String, String> requestMap);


    /**
     * 项目中统一的Get请求格式
     *
     * @param requestMap
     * @return
     */
    @GET("joke/content/list.from")
    Observable<String> get(@QueryMap Map<String, String> requestMap);

}
