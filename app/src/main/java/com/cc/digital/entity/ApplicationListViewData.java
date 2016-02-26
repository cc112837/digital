package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/12.
 */
public class ApplicationListViewData {
    private String dateline;
    private String replies;
    private String subject;
    private String tid;
    private String typeid;
    private Threadsort_Data threadsort_data;

    public ApplicationListViewData() {
    }

    public ApplicationListViewData(String dateline, String replies, String subject,
                                   String tid, String typeid, Threadsort_Data threadsort_data) {
        this.dateline = dateline;
        this.replies = replies;
        this.subject = subject;
        this.tid = tid;
        this.typeid = typeid;
        this.threadsort_data = threadsort_data;
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

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public Threadsort_Data getThreadsort_data() {
        return threadsort_data;
    }

    public void setThreadsort_data(Threadsort_Data threadsort_data) {
        this.threadsort_data = threadsort_data;
    }

    @Override
    public String toString() {
        return "ApplicationListViewData{" +
                "dateline='" + dateline + '\'' +
                ", replies='" + replies + '\'' +
                ", subject='" + subject + '\'' +
                ", tid='" + tid + '\'' +
                ", typeid='" + typeid + '\'' +
                ", threadsort_data=" + threadsort_data +
                '}';
    }
}




























