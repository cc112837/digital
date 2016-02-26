package com.cc.digital.utils;

import android.os.Handler;

import com.cc.digital.callback.MyStringCallBack;
import com.cc.digital.entity.CommunityHome;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

/**
 * Created by PENG on 2015/11/11 0011.
 */
public class CommunityHttpUtils {
    private static HttpUtils utils = new HttpUtils();

    public static HttpHandler sendDate(HttpRequest.HttpMethod method, String url, RequestParams params, RequestCallBack callBack) {
        System.out.println(url);
        System.out.println(callBack);
        return utils.send(method, url, callBack);
    }

    //
    public static HttpHandler getData(Handler handler, int what) {
        RequestParams params = new RequestParams();
        return sendDate(HttpRequest.HttpMethod.GET, "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=forum&actions=index", params, new MyStringCallBack(new CommunityHome(), handler, what));
//        return null;
    }
}
