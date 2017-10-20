package com.xwh.frame.mvp.model;

import com.xwh.frame.app.App;
import com.xwh.frame.base.BaseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xwh on 2017/10/19.
 */
public class SplashModel extends BaseModel {

    private RequestService requestService;

    public void updateApk() {
        requestService = App.retrofit.create(RequestService.class);
        Call<String> respose = requestService.checkApkVersion();
        respose.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

}
