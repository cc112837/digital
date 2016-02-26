package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/15.
 */
public class NewsFields {
    private String author;
    private String avatar;
    private String typename;
    private String views;

    public NewsFields() {
    }

    public NewsFields(String author, String avatar, String typename, String views) {
        this.author = author;
        this.avatar = avatar;
        this.typename = typename;
        this.views = views;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    @Override
    public String toString() {
        return "NewsFields{" +
                "author='" + author + '\'' +
                ", avatar='" + avatar + '\'' +
                ", typename='" + typename + '\'' +
                ", views='" + views + '\'' +
                '}';
    }
}
