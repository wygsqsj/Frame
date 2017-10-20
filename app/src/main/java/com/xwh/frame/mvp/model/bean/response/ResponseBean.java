package com.xwh.frame.mvp.model.bean.response;

import com.google.gson.JsonObject;

/**
 * Created by xwh on 2017/5/9.
 * <p/>
 * 后台数据统一格式
 */
public class ResponseBean {
    private String result;

    private String errorCode;

    private String errorDesc;


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    private JsonObject data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
