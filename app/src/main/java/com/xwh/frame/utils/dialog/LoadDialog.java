package com.xwh.frame.utils.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;

import com.xwh.frame.utils.TimerUtil;

/**
 * Created by xwh on 2017/11/24.
 */

public class LoadDialog {

    private StarDialog dialog;
    private TimerUtil timer;
    private int count = 0;
    private int showTime = 1000;//默认dialog显示时间
    private boolean isCancel = false;

    /**
     * 初始化加载弹窗
     **/
    private void initProgress(Context context, String message) {
        dialog = new StarDialog(context);
        dialog.setLoadingColor(Color.BLACK)
                .setHintText(message)
                .setHintTextColor(Color.GRAY);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                stopTimer();
            }
        });
    }

    /**
     * 初始化定时器，用于dialog的显示时间
     */
    private void initTimer() {
        timer = new TimerUtil(false);
        timer.monitorTimer(new TimerUtil.TimerListener() {
            @Override
            public void onTimerListener() {
                count++;
                if (isCancel) hideProgressDialog();
            }
        });
    }

    public void showProgressDialog(Context context, String message) {
        if (null == dialog) {
            initProgress(context, message);
        }
        if (null == timer) {
            initTimer();
        }
        timer.start(0, 1000);
        dialog.show();
        isCancel = false;
    }

    public void hideProgressDialog() {
        isCancel = true;
        if (count * 1000 > showTime && null != dialog && dialog.isShowing()) {
            dialog.cancel();
        }
    }

    public void immedHideProDialog() {
        if (null != dialog && dialog.isShowing()) {
            dialog.cancel();
        }
    }

    private void stopTimer() {
        count = 0;
        if (!timer.isStop()) {
            timer.stop();
        }
    }

    public void destroy() {
        if (null != dialog) {
            dialog.cancel();
            dialog = null;
        }
        if (timer != null && !timer.isStop()) {
            timer.stop();
            timer = null;
        }
    }

    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }


}
