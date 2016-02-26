package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotNewsData {
    private String title;
    private String url;
    private NewsFields newsFields;

    public HotNewsData() {
    }

    public HotNewsData(String title, String url, NewsFields newsFields) {
        this.title = title;
        this.url = url;
        this.newsFields = newsFields;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public NewsFields getNewsFields() {
        return newsFields;
    }

    public void setNewsFields(NewsFields newsFields) {
        this.newsFields = newsFields;
    }

    @Override
    public String toString() {
        return "HotNewsData{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", newsFields=" + newsFields +
                '}';
    }
}
