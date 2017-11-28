package com.xwh.frame.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.xwh.frame.app.App;
import com.xwh.frame.mvp.model.bean.Download;
import com.xwh.frame.utils.AppUtil;
import com.xwh.frame.utils.Constants;
import com.xwh.frame.utils.FileUtil;
import com.xwh.frame.utils.LogUtil;
import com.xwh.frame.utils.NotificationUtil;
import com.xwh.frame.utils.ToastUtil;
import com.xwh.frame.utils.net.base.DownloadProgressListener;
import com.xwh.frame.utils.net.config.HttpConstants;
import com.xwh.frame.utils.net.request.DownloadRequest;

import java.io.File;
import java.util.Date;

import rx.Subscriber;

/**
 * 下载管理服务
 * Created by xwh on 2017/11/10.
 */

public class DownService extends Service {

    private String TAG = "DownService";
    private String apkUrl, fileType, fileName;
    private Download download = new Download();
    private NotificationUtil notificationUtil = new NotificationUtil(App.getContext());

    private File apkFile;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.d(TAG, "下载服务开启");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtil.d(TAG, "in onStartCommand");
        notificationUtil.showProgress("应用开始下载,稍后请安装",
                "新版本开始下载，请稍候...",
                "",
                100,
                1);
        apkUrl = intent.getStringExtra("url");
        fileType = intent.getStringExtra("fileType");
        fileName = intent.getStringExtra("fileName");
        download();

        // 1.START_NOT_STICKY：当Service因为内存不足而被系统kill后，接下来未来的某个时间内，即使系统内存足够可用，系统也不会尝试重新创建此Service。
        //   除非程序中Client明确再次调用startService(...)启动此Service。
        // 2.START_STICKY：当Service因为内存不足而被系统kill后，接下来未来的某个时间内，当系统内存足够可用的情况下，系统将会尝试重新创建此Service，
        //   一旦创建成功后将回调onStartCommand(...)方法，但其中的Intent将是null，pendingIntent除外。
        // 3.START_REDELIVER_INTENT：与START_STICKY唯一不同的是，回调onStartCommand(...)方法时，其中的Intent将是非空，将是最后一次调用startService(...)中的intent。
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.d(TAG, "下载服务关闭");
    }

    /**
     * 下载文件
     */
    private void download() {
        DownloadProgressListener listener = new DownloadProgressListener() {
            int limit = 0;

            @Override
            public void update(long bytesRead, long contentength, boolean done) {
                LogUtil.i(TAG, "当前控制变量：" + limit);
                if (limit % 50 == 0) {
                    LogUtil.i(TAG, "刷新通知栏" + limit);
                    notificationUtil.showProgress("应用开始下载,稍后请点击安装",
                            "新版本开始下载，请稍候...",
                            "稍后点击即可安装",
                            100,
                            (int) ((bytesRead * 100) / contentength));
                }
                limit++;

            }
        };
        FileUtil.createFileDir(Constants.APK_PATH, App.getContext()); // 创建下载文件夹
        apkFile = new File(FileUtil.getFileDir(Constants.APK_PATH, App.getContext()),
                fileName + "." + fileType); // 获取下载目录 File
        String baseUrl = HttpConstants.BASE_URL;
        new DownloadRequest(baseUrl, listener)
                .downloadAPK(apkUrl, apkFile, new Subscriber() {
                    @Override
                    public void onCompleted() {
                        downloadCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        downloadError();
                        LogUtil.i(TAG, "onError: " + e.getMessage());
                        ToastUtil.showShort(App.getContext(), "下载失败！");
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }


    /**
     * 下载出错
     */
    private void downloadError() {
        notificationUtil.removeOnGoing(NotificationUtil.NOTIFICATION_DOWNLOAD);
        notificationUtil.showNormal(
                "下载出错",
                "下载出错",
                "下载出错",
                "附加内容",
                1,
                true,
                new Date().getTime(),
                "MainActivity");
        //自动清除通知
        notificationUtil.removeOnGoing(NotificationUtil.NOTIFICATION_NORMAL);
        stopSelf();
    }

    /**
     * 下载完成
     */
    private void downloadCompleted() {
        LogUtil.d(TAG, "download complete");
        notificationUtil.removeOnGoing(NotificationUtil.NOTIFICATION_DOWNLOAD);
        notificationUtil.showNormal("新版本下载完成,请安装",
                "新版本下载完成,请安装",
                "点击即可安装",
                fileName,
                1,
                true,
                new Date().getTime(),
                apkFile);
        notificationUtil.removeOnGoing(NotificationUtil.NOTIFICATION_NORMAL);
        //自动安装APK
        AppUtil.installApk(App.getContext(), apkFile);
        stopSelf();
    }
}
