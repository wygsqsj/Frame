package com.xwh.frame.net;

import android.os.Build;

import com.xwh.frame.app.App;
import com.xwh.frame.utils.AppUtil;
import com.xwh.frame.utils.NetConnectUtil;
import com.xwh.frame.utils.SharedPreUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

/**
 * Created by xwh on 2017/10/20.
 * 请求参数配置类，封装统一的请求格式
 */
public class HttpParams {
    private static HttpParams params;
    private JSONObject infoJson;
    private JSONObject bizJson;
    private JSONObject mainJson;

    private HttpParams() {
        infoJson = new JSONObject();
        mainJson = new JSONObject();
        bizJson = new JSONObject();
        try {
            bizJson.put("version", AppUtil.getVersionName(App.getContext()));
            bizJson.put("deviceType", "1");
            bizJson.put("deviceName", Build.BRAND + "," + Build.MODEL);
            bizJson.put("deviceToken",
                    SharedPreUtil.getString(App.getContext(), "token", ""));
            bizJson.put("deviceId", getId());
            bizJson.put("deviceOs", Build.VERSION.RELEASE);
            bizJson.put("ipType",
                    NetConnectUtil.getNetworkTypeName(App.getContext()));
            infoJson.put("biz", bizJson);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HttpParams obtion() {
        if (params == null) {
            synchronized (HttpParams.class) {
                if (params == null) {
                    params = new HttpParams();
                }
            }
        }
        return params;
    }

    public HttpParams setParams(String key, String value) {
        try {
            mainJson.put(key, value);
            return this;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public HttpParams setMethod(String method) {
        try {
            infoJson.put("method", method);
            return this;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 设置完参数之后，最终调用，获取到最终的HttpParams
     *
     * @return
     */
    public HttpParams build() {
        try {
            infoJson.put("main", mainJson);
            return this;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getInfoJson() {
        return infoJson.toString();
    }

    /**
     * 获取用户的IMEI,如果获取不到,使用UUID替代
     *
     * @return IMEI or UUID
     */
    public String getId() {
        String IMEI = AppUtil.getIMEI(App.getContext());
        if ("".equals(IMEI)) {
            String nowUUID =
                    SharedPreUtil.getString(App.getContext(), "UUID", "");
            if ("".equals(nowUUID)) {
                String newUUID = UUID.randomUUID().toString();
                SharedPreUtil.putString(App.getContext(), "UUID", newUUID);
                return newUUID;
            } else {
                return nowUUID;
            }
        } else {
            return IMEI;
        }
    }
}
