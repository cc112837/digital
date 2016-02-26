package com.cc.digital.callback;

import android.os.Handler;
import android.os.Message;

import com.cc.digital.entity.ApplicationListViewData;
import com.cc.digital.entity.ApplicationViewPagerData;
import com.cc.digital.entity.ArticleFields;
import com.cc.digital.entity.Diy_data;
import com.cc.digital.entity.HotApplicationData;
import com.cc.digital.entity.HotArticleData;
import com.cc.digital.entity.HotNewsData;
import com.cc.digital.entity.NewsFields;
import com.cc.digital.entity.Threadsort_Data;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 网络请求的回调
 */
public class ApplicationStringCallBack extends RequestCallBack<String> {
    private Handler handler;//返回数据对应页面的handler
    private int what;//标记消息的what值

    public static List<ApplicationViewPagerData> viewPagerDataList = new ArrayList<>();
    private static List<ApplicationListViewData> listViewDataList = new ArrayList<>();
    private static List<HotArticleData> articleDataList = new ArrayList<>();
    private static List<HotNewsData> newsDataList = new ArrayList<>();
    private static  List<HotApplicationData> applicationDataList = new ArrayList<>();

    public ApplicationStringCallBack(Handler handler, int what) {
        this.handler = handler;
        this.what = what;
    }

    @Override
    public void onSuccess(ResponseInfo<String> responseInfo) {
        String result = responseInfo.result;
        if (what == 100){
            try {
                JSONObject object = new JSONObject(result);
                JSONObject returnData = object.getJSONObject("returnData");
                JSONObject blocklist = returnData.getJSONObject("blocklist");
                JSONObject jsonObject = blocklist.getJSONObject("360");
                Iterator<String> keys = jsonObject.keys();
                while(keys.hasNext()){
                    viewpagerJson(jsonObject,keys.next());
                }

                Message message = Message.obtain();
                message.what = what;
                message.obj = viewPagerDataList;
                handler.sendMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else if (what == 200 || what == 400){
            try {
                JSONObject object = new JSONObject(result);
                JSONObject threadlist = object.getJSONObject("threadlist");

                Iterator<String> keys = threadlist.keys();
                while (keys.hasNext()){
                    listviewJson(threadlist,keys.next());
                }

                Message message = Message.obtain();
                message.obj = listViewDataList;
                message.what = what;
                handler.sendMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(what == 101){
            try {
                JSONObject object = new JSONObject(result);
                JSONObject returnData = object.getJSONObject("returnData");
                JSONObject blocklist = returnData.getJSONObject("blocklist");
                JSONObject jsonObject = blocklist.getJSONObject("353");

                Iterator<String> keys = jsonObject.keys();
                while(keys.hasNext()){
                    articleJson(jsonObject, keys.next());
                }

                Message message = Message.obtain();
                message.what = what;
                message.obj = articleDataList;
                handler.sendMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        else if(what == 111){
            try {
                JSONObject object = new JSONObject(result);
                JSONObject returnData = object.getJSONObject("returnData");
                JSONObject blocklist = returnData.getJSONObject("blocklist");
                JSONObject jsonObject = blocklist.getJSONObject("369");

                Iterator<String> keys = jsonObject.keys();
                while(keys.hasNext()){
                    newsJson(jsonObject, keys.next());
                }

                Message message = Message.obtain();
                message.what = what;
                message.obj = newsDataList;
                handler.sendMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else if(what == 121){
            try {
                JSONObject object = new JSONObject(result);
                JSONObject jsonObject = object.getJSONObject("threadlist");

                Iterator<String> keys = jsonObject.keys();
                while(keys.hasNext()){
                    applicationJson(jsonObject, keys.next());
                }
                Message message = Message.obtain();
                message.what = what;
                message.obj = applicationDataList;
                handler.sendMessage(message);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void applicationJson(JSONObject jsonObject, String number) {
        try {
            JSONObject item = jsonObject.getJSONObject(number);
            String subject = item.getString("subject");

            JSONObject item2 = item.getJSONObject("diy_data");
            String picUrl = item2.getString("picurl");
            String url = item2.getString("url");

            Diy_data diy_data = new Diy_data(picUrl,url);
            HotApplicationData hotApplicationData = new HotApplicationData(subject,diy_data);
            applicationDataList.add(hotApplicationData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void newsJson(JSONObject jsonObject, String number) {
        try {
            JSONObject item = jsonObject.getJSONObject(number);
            String title = item.getString("title");
            String url = item.getString("url");

            JSONObject item2 = item.getJSONObject("fields");
            String author = item2.getString("author");
            String avatar = item2.getString("avatar");
            String typename = item2.getString("typename");
            String views = item2.getString("views");

            NewsFields newsFields = new NewsFields(author,avatar,typename,views);
            HotNewsData hotNewsData = new HotNewsData(title,url,newsFields);
            newsDataList.add(hotNewsData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void articleJson(JSONObject jsonObject, String number) {
        try {
            JSONObject item = jsonObject.getJSONObject(number);
            String pic_url = item.getString("pic_url");
            String summary = item.getString("summary");
            String title = item.getString("title");
            String url = item.getString("url");

            JSONObject item2 = item.getJSONObject("fields");
            String dateline = item2.getString("dateline");
            String catname = item2.getString("catname");
            String viewnum = item2.getString("viewnum");

            ArticleFields articleFields = new ArticleFields(dateline,catname,viewnum);
            HotArticleData hotArticleData = new HotArticleData(pic_url,summary,title,url,articleFields);
            articleDataList.add(hotArticleData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void viewpagerJson(JSONObject jsonObject,String number){
        try {
            JSONObject item = jsonObject.getJSONObject(number);
            String id = item.getString("id");
            String title = item.getString("title");
            String pic_url = item.getString("pic_url");
            String url = item.getString("url");

            ApplicationViewPagerData viewPagerData = new ApplicationViewPagerData(id,title,pic_url,url);
            viewPagerDataList.add(viewPagerData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void listviewJson(JSONObject jsonObject,String number){
        try {
            JSONObject item1 = jsonObject.getJSONObject(number);
            String dateline = item1.getString("dateline");
            String replies = item1.getString("replies");
            String subject = item1.getString("subject");
            String tid = item1.getString("tid");
            String typeid = item1.getString("typeid");

            JSONObject item2 = item1.getJSONObject("threadsort_data");
            String app_brief = item2.getString("App_brief");
            String app_pic = item2.getString("App_pic");
            String app_platform = item2.getString("App_platform");

            Threadsort_Data threadsort_data = new Threadsort_Data(app_brief,app_pic,app_platform);
            ApplicationListViewData listViewData = new ApplicationListViewData(dateline,replies,subject,
                                                        tid,typeid,threadsort_data);

            listViewDataList.add(listViewData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailure(HttpException error, String msg) {

    }
}
























