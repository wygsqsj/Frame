package com.xwh.frame.mvp.model.bean;

import java.util.List;

import static android.R.attr.id;

/**
 * Created by XH on 2017/10/24.
 */

public class BeforeToday {

    private List<ResultBean> list;

    public List<ResultBean> getList() {
        return list;
    }

    public void setList(List<ResultBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "BeforeToday{" +
                "result=" + list +
                '}';
    }

    public static class ResultBean {
        private int day;
        private String des;
        private String _id;
        private String lunar;
        private int month;
        private String pic;
        private String title;
        private int year;

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getId() {
            return _id;
        }

        public void setId(String id) {
            this._id = id;
        }

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "day=" + day +
                    ", des='" + des + '\'' +
                    ", id=" + id +
                    ", lunar='" + lunar + '\'' +
                    ", month=" + month +
                    ", pic='" + pic + '\'' +
                    ", title='" + title + '\'' +
                    ", year=" + year +
                    '}';
        }
    }
}
