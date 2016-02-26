package com.cc.digital.entity;

/**
 * Created by Administrator on 2015/11/12.
 */
public class Threadsort_Data {
    private String App_brief;
    private String App_pic;
    private String App_platform;

    public Threadsort_Data() {
    }

    public Threadsort_Data(String app_brief, String app_pic, String app_platform) {
        App_brief = app_brief;
        App_pic = app_pic;
        App_platform = app_platform;
    }

    public String getApp_brief() {
        return App_brief;
    }

    public void setApp_brief(String app_brief) {
        App_brief = app_brief;
    }

    public String getApp_pic() {
        return App_pic;
    }

    public void setApp_pic(String app_pic) {
        App_pic = app_pic;
    }

    public String getApp_platform() {
        return App_platform;
    }

    public void setApp_platform(String app_platform) {
        App_platform = app_platform;
    }

    @Override
    public String toString() {
        return "Threadsort_Data{" +
                "App_brief='" + App_brief + '\'' +
                ", App_pic='" + App_pic + '\'' +
                ", App_platform='" + App_platform + '\'' +
                '}';
    }
}
