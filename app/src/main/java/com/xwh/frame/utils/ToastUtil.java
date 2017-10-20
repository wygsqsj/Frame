package com.xwh.frame.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xwh on 2017/10/20.
 */
public class ToastUtil {

    private static Toast toast = null; //Toast的对象！

    public static void showToast(Context mContext, String str, int duration) {
        if (toast == null) {
            toast = Toast.makeText(mContext, str, duration);
        } else {
            toast.setText(str);
        }
        toast.show();
    }

    /**
     * 短时间显示Toast
     *
     * @param str 要显示的内容
     */
    public static void showShort(Context mContext, String str) {
        showToast(mContext, str, Toast.LENGTH_SHORT);
    }

    /**
     * 长时间显示Toast
     *
     * @param str 要显示的内容
     */
    public static void showLong(Context mContext, String str) {
        showToast(mContext, str, Toast.LENGTH_LONG);
    }
}
