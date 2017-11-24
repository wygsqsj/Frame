package com.xwh.frame.utils.net.base;

import com.xwh.frame.mvp.model.bean.ResponseBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * retrofit接口控制请求类
 * Created by xwh on 2017/5/9.
 */
public interface Service {

//    /**
//     * 项目中统一的Post请求格式
//     * 在正式的项目中，一般这种方式是应用比较广泛的，由一个统一的入口进入
//     * 将方法及参数封装到请求的json串中转换为Stirng加密后进行传递
//     *
//     * @param requestBody 请求数据数据的String串
//     * @return Observable<ResponseBean>
//     */
//    @Headers({"Content-Type: application/json", "Accept: application/json"})
//    @POST("main") //每个项目入口不同，更改此处即可
//    Observable<ResponseBean> post(@Body String requestBody);

    /**
     * 项目中统一的Post请求格式
     *
     * @return Observable<ResponseBean>
     */
    @FormUrlEncoded
    @POST("japi/toh")
    Observable<ResponseBean> post(@FieldMap Map<String, String> requestMap);

    /**
     * 项目中统一的Get请求格式，无参
     * 路径以参数的形式传入
     *
     * @return
     */
    @GET
    Observable<ResponseBean> get(@Url String path);

    /**
     * 项目中统一的Get请求格式
     * 路径以参数的形式传入
     *
     * @return
     */
    @GET
    Observable<ResponseBean> get(@Url String url,
                                 @QueryMap(encoded = true) Map<String, String> options);

}
