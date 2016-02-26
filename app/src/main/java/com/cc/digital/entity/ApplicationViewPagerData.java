package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/12.
 */
public class ApplicationViewPagerData {
    private String id;
    private String title;
    private String pic_url;
    private String url;

    public ApplicationViewPagerData() {
    }

    public ApplicationViewPagerData(String id, String title, String pic_url,String url) {
        this.id = id;
        this.title = title;
        this.pic_url = pic_url;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "ApplicationViewPagerData{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", pic_url='" + pic_url + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
