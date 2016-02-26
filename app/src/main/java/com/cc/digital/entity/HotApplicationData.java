package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotApplicationData {
    private String subject;
    private Diy_data diy_data;

    public HotApplicationData() {
    }

    public HotApplicationData(String subject, Diy_data diy_data) {
        this.subject = subject;
        this.diy_data = diy_data;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Diy_data getDiy_data() {
        return diy_data;
    }

    public void setDiy_data(Diy_data diy_data) {
        this.diy_data = diy_data;
    }

    @Override
    public String toString() {
        return "HotApplicationData{" +
                "subject='" + subject + '\'' +
                ", diy_data=" + diy_data +
                '}';
    }
}
