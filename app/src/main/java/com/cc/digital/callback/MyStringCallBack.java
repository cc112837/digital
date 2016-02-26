package com.cc.digital.callback;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

/**
 * Created by PENG on 2015/11/5 0005.
 */
public class MyStringCallBack extends RequestCallBack<String> {
    private Object object;//解析数据的具体对象
    private Handler handler;//返回数据到对应页面的 handler
    private int what;//标记消息的 what值,用于区分分页等,比如是追加数据还是创建新的 adapter 等

    public MyStringCallBack(Object object, Handler handler, int what) {
        this.object = object;
        this.handler = handler;
        this.what = what;
    }

    @Override
    public void onSuccess(ResponseInfo<String> responseInfo) {
        object = new Gson().fromJson(responseInfo.result, object.getClass());//根据传递进来的对象解析数据

        Message message = handler.obtainMessage();
        message.what = what;
        message.obj = object;
        handler.sendMessage(message);
    }

    @Override
    public void onFailure(HttpException error, String msg) {
        Log.e("xutils", msg);
    }
}