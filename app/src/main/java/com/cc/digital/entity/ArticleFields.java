package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/15.
 */
public class ArticleFields {
    private String dateline;
    private String catname;
    private String viewnum;

    public ArticleFields() {
    }

    public ArticleFields(String dateline, String catname, String viewnum) {
        this.dateline = dateline;
        this.catname = catname;
        this.viewnum = viewnum;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }

    public String getViewnum() {
        return viewnum;
    }

    public void setViewnum(String viewnum) {
        this.viewnum = viewnum;
    }
}
