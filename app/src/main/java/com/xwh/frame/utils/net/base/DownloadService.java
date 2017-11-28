package com.xwh.frame.utils.net.base;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Retrofit下载管理请求
 * Created by xwh on 2017/11/10.
 */

public interface DownloadService {
    @Streaming
    @GET()
    Observable<ResponseBody> download(@Url String path);
}
