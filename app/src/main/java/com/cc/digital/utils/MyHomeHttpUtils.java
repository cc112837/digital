package com.cc.digital.utils;

import android.content.Context;
import android.os.Handler;

import com.cc.digital.callback.MyHomeCallback;
import com.cc.digital.inter.Inter;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.client.HttpRequest;


/**
 * Created by Administrator on 2015/11/11.
 */
public class MyHomeHttpUtils {

    private static int page=0;
    private static int newspage=0;

    public static void handData(Handler handler, int what,Context context) {
        HttpUtils myhttpUtils = new HttpUtils();
        if (what == 1) {
            myhttpUtils.send(HttpRequest.HttpMethod.GET, Inter.homePicUri, new MyHomeCallback(handler, what,context));
        } else if (what == 2) {
            myhttpUtils.send(HttpRequest.HttpMethod.GET, Inter.homeHomeUri, new MyHomeCallback(handler, what,context));
        }else if(what==4){
            page+=20;
            String uri= Inter.base+"article&limit="+page+"_20&order=dateline_desc";
            myhttpUtils.send(HttpRequest.HttpMethod.GET,uri, new MyHomeCallback(handler, what,context));
        }else if(what==8){
            myhttpUtils.send(HttpRequest.HttpMethod.GET, Inter.baseNewsUri+"0_20&fid=63&REQUESTCODE=5", new MyHomeCallback(handler, what,context));
        }else if(what==11){
            newspage+=20;
            myhttpUtils.send(HttpRequest.HttpMethod.GET, Inter.baseNewsUri+newspage+"_20&fid=63&REQUESTCODE=5", new MyHomeCallback(handler, what,context));
        }

    }
    public static void handData(Handler handler,int what,String uid,Context context){
        HttpUtils myhttpUtils = new HttpUtils();
        if(what==7){
            String uri=Inter.base+"view&aid="+uid;
            myhttpUtils.send(HttpRequest.HttpMethod.GET,uri,new MyHomeCallback(handler,what,context));
        }
    }

}
