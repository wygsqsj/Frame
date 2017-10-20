package com.xwh.frame.net;

import android.graphics.Bitmap;

import com.xwh.frame.mvp.model.bean.response.ResponseBean;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by xwh on 2017/5/9.
 */
public abstract class Service {
    /**
     * 项目中统一的请求格式
     *
     * @param infoJson
     * @return
     */
    @FormUrlEncoded
    @POST("crf_finance/main.do")
    public abstract Observable<ResponseBean> mainRequest(@Field("infoJson") String infoJson);

    /**
     * 下载图片
     *
     * @param imgUrl
     * @return Bitmap
     */
    @GET
    public abstract Observable<Bitmap> getBitmap(@Url String imgUrl);

}
