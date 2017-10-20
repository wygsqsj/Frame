package com.xwh.frame.mvp.model.bean;

import java.util.List;

/**
 * Created by xwh on 2017/5/12.
 */
public class SplashImg {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
