package com.xwh.frame.utils.net.request;

import android.support.annotation.NonNull;

import com.xwh.frame.utils.FileUtil;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.net.base.DownloadProgressListener;
import com.xwh.frame.utils.net.base.DownloadService;
import com.xwh.frame.utils.net.config.DownloadProgressInterceptor;
import com.xwh.frame.utils.net.config.HttpConstants;
import com.xwh.frame.utils.net.config.ResultException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * 下载请求
 * Created by xwh on 2017/11/10.
 */
public class DownloadRequest {

    private static final String TAG = "DownloadRequest";
    private static final int DEFAULT_TIMEOUT = 15;
    public Retrofit retrofit;


    public DownloadRequest(String url, DownloadProgressListener listener) {

        DownloadProgressInterceptor interceptor = new DownloadProgressInterceptor(listener);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(HttpConstants.BASE_URL)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    public void downloadAPK(@NonNull String url, final File file, Subscriber subscriber) {
        LogUtil.i(TAG, "downloadAPK: " + url);

        retrofit.create(DownloadService.class)
                .download(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Func1<ResponseBody, InputStream>() {
                    @Override
                    public InputStream call(ResponseBody responseBody) {
                        return responseBody.byteStream();
                    }
                })
                .observeOn(Schedulers.computation())
                .doOnNext(new Action1<InputStream>() {
                    @Override
                    public void call(InputStream inputStream) {
                        try {
                           FileUtil.writeFile(inputStream, file);
                        } catch (IOException e) {
                            e.printStackTrace();
                            throw new ResultException(HttpConstants.WRITE_FILE_ERROR, e.toString());
                        }
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }
}
