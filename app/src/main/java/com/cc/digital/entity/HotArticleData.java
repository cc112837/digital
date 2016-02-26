package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotArticleData {
    private String pic_url;
    private String summary;
    private String title;
    private String url;
    private ArticleFields articleFields;

    public HotArticleData() {

    }

    public HotArticleData(String pic_url, String summary, String title, String url, ArticleFields articleFields) {
        this.pic_url = pic_url;
        this.summary = summary;
        this.title = title;
        this.url = url;
        this.articleFields = articleFields;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
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

    public ArticleFields getArticleFields() {
        return articleFields;
    }

    public void setArticleFields(ArticleFields articleFields) {
        this.articleFields = articleFields;
    }

    @Override
    public String toString() {
        return "HotArticleData{" +
                "pic_url='" + pic_url + '\'' +
                ", summary='" + summary + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", articleFields=" + articleFields +
                '}';
    }
}
