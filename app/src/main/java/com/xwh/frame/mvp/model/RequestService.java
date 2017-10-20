package com.xwh.frame.mvp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by xwh on 2017/10/20.
 */
public interface RequestService {

    @Headers("checkApk:0")
    @GET("crf_finance/apkUpdate.do")
    Call<String> checkApkVersion();

}
