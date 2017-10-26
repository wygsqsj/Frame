package com.xwh.frame.mvp.model.bean;

import com.google.gson.JsonObject;

/**
 * Created by xwh on 2017/5/9.
 * <p/>
 * 后台数据统一格式,result必须为JsonObject，不能直接放JsonArray
 * 处理方式没有想到，后期再处理，如果项目刚开始搭建，一定要和后台商量好
 * result中放键值对形式否则会解析失败
 */
public class ResponseBean {

    private String error_code;

    private String reason;

    private JsonObject result;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public JsonObject getResult() {
        return result;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }


}
