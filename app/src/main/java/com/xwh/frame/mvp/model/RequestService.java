package com.xwh.frame.mvp.model;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by xwh on 2017/10/20.
 */
public interface RequestService {

    @GET("crf_finance/apkUpdate.do")
    Call<String> checkApkVersion();

}
