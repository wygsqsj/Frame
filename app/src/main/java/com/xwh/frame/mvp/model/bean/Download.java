package com.xwh.frame.mvp.model.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 下载的进度管理实体
 * Created by xwh on 2017/11/10.
 */

public class Download implements Parcelable {

    private int progress;
    private long currentFileSize;
    private long totalFileSize;

    public Download() {
    }

    protected Download(Parcel in) {
        progress = in.readInt();
        currentFileSize = in.readLong();
        totalFileSize = in.readLong();
    }

    public static final Creator<Download> CREATOR = new Creator<Download>() {
        @Override
        public Download createFromParcel(Parcel in) {
            return new Download(in);
        }

        @Override
        public Download[] newArray(int size) {
            return new Download[size];
        }
    };

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public long getCurrentFileSize() {
        return currentFileSize;
    }

    public void setCurrentFileSize(long currentFileSize) {
        this.currentFileSize = currentFileSize;
    }

    public long getTotalFileSize() {
        return totalFileSize;
    }

    public void setTotalFileSize(long totalFileSize) {
        this.totalFileSize = totalFileSize;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(progress);
        dest.writeLong(currentFileSize);
        dest.writeLong(totalFileSize);
    }
}
