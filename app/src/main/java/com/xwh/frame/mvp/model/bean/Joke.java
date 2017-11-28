package com.xwh.frame.mvp.model.bean;

import java.util.List;

/**
 * Created by xwh on 2017/10/26.
 */

public class Joke {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String content;
        private String hashId;
        private int unixtime;
        private String updatetime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHashId() {
            return hashId;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public int getUnixtime() {
            return unixtime;
        }

        public void setUnixtime(int unixtime) {
            this.unixtime = unixtime;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }


    }

    @Override
    public String toString() {
        return "Joke{" +
                "data=" + data +
                '}';
    }
}
