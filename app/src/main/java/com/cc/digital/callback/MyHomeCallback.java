package com.cc.digital.callback;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.cc.digital.entity.HomeContent;
import com.cc.digital.entity.NewsContent;
import com.cc.digital.entity.PicCir;
import com.cc.digital.entity.Specific;
import com.cc.digital.utils.FileSave;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
public class MyHomeCallback extends RequestCallBack<String> {
    public static List<PicCir> piclist = new ArrayList<>();
    private static List<HomeContent> homelist = new ArrayList<>();
    private static List<NewsContent> newslist = new ArrayList<>();
    private Handler handler;
    private int what;
    private Context context;
    private static String[] strings;
    private static List<String> list;

    public MyHomeCallback(Handler handler, int what, Context context) {
        this.handler = handler;
        this.what = what;
        this.context = context;
    }

    @Override
    public void onSuccess(ResponseInfo<String> responseInfo) {
        String result = responseInfo.result;
        Log.i("数据是", result);
        if (what == 1) {
            try {
                FileSave.write(result, "homejson1", context);
                jsonHome1(result);
                Message msg = Message.obtain();
                msg.obj = piclist;
                msg.what = what;
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (what == 2 || what == 4) {
            try {
                if (what == 2) {
                    FileSave.write(result, "homejson2", context);
                }
                jsonHome2(result);
                Message msg = Message.obtain();
                msg.obj = homelist;
                msg.what = what;
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (what == 8 || what == 11) {
            if (what == 8) {
                FileSave.write(result, "newsjson8", context);
            }
            try {
                newsJson8(result);
                Message msg = Message.obtain();
                msg.obj = newslist;
                msg.what = what;
                handler.sendMessage(msg);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (what == 7) {
            try {
                Log.i("具体界面的数据是", result);
                JSONObject object = new JSONObject(result);
                JSONObject returnData = object.getJSONObject("returnData");
                JSONObject articledata = returnData.getJSONObject("articledata");
                String title = articledata.getString("title");
                String dateline = articledata.getString("dateline");
                String author = articledata.getString("author");
                String summary = articledata.getString("summary");
                String pic = articledata.getString("pic");
                JSONObject article_content = returnData.getJSONObject("article_content");
                String content = article_content.getString("content");
                Specific specific = new Specific(title, dateline, author, summary, pic, content);
                Message msg = Message.obtain();
                msg.obj = specific;
                msg.what = what;
                handler.sendMessage(msg);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static Object newsJson8(String result) throws JSONException {
        JSONObject obj = new JSONObject(result);
        JSONObject threadlist = obj.getJSONObject("threadlist");
        Iterator<String> keys = threadlist.keys();
        while (keys.hasNext()) {
            Log.i("key值是",keys.next());
            JSONObject object = threadlist.getJSONObject(keys.next());
            String subject = object.getString("subject");
            String dateline = object.getString("dateline");
            String replies = object.getString("replies");
            JSONObject threadsort_data = object.getJSONObject("threadsort_data");
            String pushedaid = object.getString("pushedaid");
            String app_brief = threadsort_data.getString("App_brief");
            String app_pic = threadsort_data.getString("App_pic");
            String substring = app_pic.substring(app_pic.indexOf("h"), app_pic.lastIndexOf("g")+1);
            NewsContent newsContent = new NewsContent(subject, dateline, replies, app_brief, substring,pushedaid);
            newslist.add(newsContent);
        }
        return newslist;
    }

    public static Object jsonHome2(String result) throws JSONException {
        JSONObject object = new JSONObject(result);
        JSONObject returnData = object.getJSONObject("returnData");
        JSONObject articlelist = returnData.getJSONObject("articlelist");
        Iterator<String> keys = articlelist.keys();
        list = new ArrayList();
        while (keys.hasNext()) {
            list.add(keys.next());
        }
        strings = list.toArray(new String[list.size()]);
        Arrays.sort(strings);
        for (int i = strings.length - 1; i >= 0; i--) {
            homeJson(articlelist, strings[i]);
        }

        return homelist;
    }

    public static Object jsonHome1(String result) throws JSONException {
        JSONObject object = new JSONObject(result);
        JSONObject returnData = object.getJSONObject("returnData");
        JSONObject blocklist = returnData.getJSONObject("blocklist");
        JSONObject jsonObject = blocklist.getJSONObject("274");
        Iterator<String> keys = jsonObject.keys();
        while (keys.hasNext()) {
            headJson(jsonObject, keys.next());
        }
        return piclist;
    }

    private static void headJson(JSONObject jsonObject, String number) throws JSONException {
        JSONObject item1 = jsonObject.getJSONObject(number);
        String title = item1.getString("title");
        String pic_url = item1.getString("pic_url");
        String id = item1.getString("id");
        PicCir picCir = new PicCir(id, title, pic_url);
        piclist.add(picCir);
    }

    private static void homeJson(JSONObject jsonObject, String number) throws JSONException {
        JSONObject item = jsonObject.getJSONObject(number);
        String aid = item.getString("aid");
        String title = item.getString("title");
        String pic_url = item.getString("pic_url");
        String summary = item.getString("summary");
        String dateline = item.getString("dateline");
        String commentnum = item.getString("commentnum");
        String favtimes = item.getString("catid");
        HomeContent homeContent = new HomeContent(aid, title, summary, dateline, commentnum, favtimes, pic_url);
        homelist.add(homeContent);

    }

    @Override
    public void onFailure(HttpException error, String msg) {
        Log.i("联网失败", "请重新加载");

        String homejson2 = FileSave.read("homejson2", context);
        String homejson1 = FileSave.read("homejson1", context);
        String newsjson8 = FileSave.read("newsjson8", context);
        try {
            Message message = Message.obtain();
            if (what == 2) {
                Object o = MyHomeCallback.jsonHome2(homejson2);
                message.what = 2;
                message.obj = o;
            }
            if (what == 1) {
                Object o1 = MyHomeCallback.jsonHome1(homejson1);
                message.what = 1;
                message.obj = o1;
            }
            if (what == 8) {
                Object o2 = MyHomeCallback.newsJson8(newsjson8);
                message.what = 8;
                message.obj = o2;
            }
            handler.sendMessage(message);
        } catch (JSONException e) {
            e.printStackTrace();

        }
    }

}
