package com.xwh.frame.utils.net.base;

/**
 * Created by xwh on 2017/11/28.
 * 下载文件进度监听器
 */

public interface DownloadProgressListener {
    void update(long bytesRead, long contentLength, boolean done);
}
