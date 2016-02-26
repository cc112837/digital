package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/12.
 */
public class Specific {
    private String title ;
    private String dateline;
    private String author;
    private String summary;
    private String pic;
    private String content;

    public Specific(String title, String dateline, String author, String summary, String pic, String content) {
        this.title = title;
        this.dateline = dateline;
        this.author = author;
        this.summary = summary;
        this.pic = pic;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Specific{" +
                "title='" + title + '\'' +
                ", dateline='" + dateline + '\'' +
                ", author='" + author + '\'' +
                ", summary='" + summary + '\'' +
                ", pic='" + pic + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
