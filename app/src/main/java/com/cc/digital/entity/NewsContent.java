package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/14.
 */
public class NewsContent {
    private String subject;
    private String dateline;
    private String replies;
    private String app_brief;
    private String substring;
    private String pushedaid;

    @Override
    public String toString() {
        return "NewsContent{" +
                "subject='" + subject + '\'' +
                ", dateline='" + dateline + '\'' +
                ", replies='" + replies + '\'' +
                ", app_brief='" + app_brief + '\'' +
                ", substring='" + substring + '\'' +
                ", pushedaid='" + pushedaid + '\'' +
                '}';
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getReplies() {
        return replies;
    }

    public void setReplies(String replies) {
        this.replies = replies;
    }

    public String getApp_brief() {
        return app_brief;
    }

    public void setApp_brief(String app_brief) {
        this.app_brief = app_brief;
    }

    public String getSubstring() {
        return substring;
    }

    public void setSubstring(String substring) {
        this.substring = substring;
    }

    public String getPushedaid() {
        return pushedaid;
    }

    public void setPushedaid(String pushedaid) {
        this.pushedaid = pushedaid;
    }

    public NewsContent(String subject, String dateline, String replies, String app_brief, String substring, String pushedaid) {
        this.subject = subject;
        this.dateline = dateline;
        this.replies = replies;
        this.app_brief = app_brief;
        this.substring = substring;
        this.pushedaid = pushedaid;
    }
}
