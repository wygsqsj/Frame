package com.xwh.frame.utils.net.config;

import com.xwh.frame.utils.net.base.DownloadProgressListener;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * okHttp拦截器,添加我们自定义的数据处理器
 * Created by xwh on 2017/11/10.
 */
public class DownloadProgressInterceptor implements Interceptor {

    private DownloadProgressListener listener;

    public DownloadProgressInterceptor(DownloadProgressListener listener) {
        this.listener = listener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());

        return originalResponse.newBuilder()
                .body(new DownloadProgressResponseBody(originalResponse.body(), listener))
                .build();
    }
}
