package com.xwh.frame.mvp.model.bean;

import okhttp3.Response;

/**
 * Created by xwh on 2017/5/9.
 * <p/>
 * 后台数据统一格式
 */
public class ResponseBean {

    private String errorCode;

    private String reason;

//    private JsonObject result;

    private Response result;

    public Response getResult() {
        return result;
    }

    public void setResult(Response result) {
        this.result = result;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

   /* public JsonObject getResult() {
        return result;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }*/
}
