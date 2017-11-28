package com.xwh.frame.mvp.model.bean;

/**
 * 版本检查更新
 * Created by xwh on 2017/11/28.
 */

public class UpDateApk {
    /**
     * 网络 apk 版本号
     */
    public String versionCode;
    /**
     * 网络 apk 版本名称
     */
    public String versionName;
    /**
     * 网络 apk 升级描述
     */
    public String brief;
    /**
     * 网络 apk 下载地址
     */
    public String url;

    @Override
    public String toString() {
        return "UpDateApk{" +
                "versionCode='" + versionCode + '\'' +
                ", versionName='" + versionName + '\'' +
                ", brief='" + brief + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
