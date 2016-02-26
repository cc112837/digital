package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/11.
 */
public class HomeContent {
    private String aid;
    private String title;
    private String summary;
    private String dateline;
    private String commentnum;
    private String favtimes;
    private String pic_url;

    public HomeContent(String aid, String title, String summary, String dateline, String commentnum, String favtimes, String pic_url) {
        this.aid = aid;
        this.title = title;
        this.summary = summary;
        this.dateline = dateline;
        this.commentnum = commentnum;
        this.favtimes = favtimes;
        this.pic_url = pic_url;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
    }

    public String getFavtimes() {
        return favtimes;
    }

    public void setFavtimes(String favtimes) {
        this.favtimes = favtimes;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    @Override
    public String toString() {
        return "HomeContent{" +
                "aid='" + aid + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", dateline='" + dateline + '\'' +
                ", commentnum='" + commentnum + '\'' +
                ", favtimes='" + favtimes + '\'' +
                ", pic_url='" + pic_url + '\'' +
                '}';
    }
}
