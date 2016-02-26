package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/15.
 */
public class Diy_data {
    private String picurl;
    private String url;

    public Diy_data() {
    }

    public Diy_data(String picurl, String url) {
        this.picurl = picurl;
        this.url = url;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Diy_data{" +
                "picurl='" + picurl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
