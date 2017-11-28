package com.xwh.frame.utils;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * 常见方法工具类
 * Created by xwh on 2017/11/28.
 */

public class SystemUtil {

    public static int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return ContextCompat.getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }

}
