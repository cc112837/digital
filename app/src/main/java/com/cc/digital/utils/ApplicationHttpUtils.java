package com.cc.digital.utils;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;


/**
 * 应用界面中Http网络请求工具类
 */
public class ApplicationHttpUtils {
    private static HttpUtils utils = new HttpUtils();

    /**
     * 发送网络请求
     * @param method   请求的方式
     * @param url      请求的地址
     * @param params   post请求的参数
     * @param callBack 请求的回调
     */
    public static void sendData(HttpRequest.HttpMethod method,String url,
                                RequestParams params,RequestCallBack callBack){
        if (method == HttpRequest.HttpMethod.GET){
            utils.send(method,url,callBack);
        }else{
            utils.send(method,url,params,callBack);
        }
    }

}



























