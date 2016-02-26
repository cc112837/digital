package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/11.
 */
public class PicCir {
    private String id;
    private String title;
    private String pic_url;

    public PicCir(String id, String title, String pic_url) {
        this.id = id;
        this.title = title;
        this.pic_url = pic_url;
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

    @Override
    public String toString() {
        return "PicCir{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", pic_url='" + pic_url + '\'' +
                '}';
    }
}
