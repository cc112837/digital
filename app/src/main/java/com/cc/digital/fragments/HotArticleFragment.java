package com.cc.digital.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cc.digital.MainActivity;
import com.cc.digital.R;
import com.cc.digital.WebViewActivity;
import com.cc.digital.adapters.HotArticleAdapter;
import com.cc.digital.callback.ApplicationStringCallBack;
import com.cc.digital.entity.HotArticleData;
import com.cc.digital.utils.ApplicationHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotArticleFragment extends Fragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView article_listView;
    private HotArticleAdapter hotArticleAdapter;
    private List<HotArticleData> articleList;
    private MainActivity activity;
    private String articleUrl = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=portal&limit=0_20&bid=353&actions=diydata";
    private String baseUrl = "http://www.dgtle.com/";

    public HotArticleFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_article, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        ApplicationHttpUtils.sendData(HttpRequest.HttpMethod.GET,articleUrl,null,new ApplicationStringCallBack(handler,101));

        article_listView = ((PullToRefreshListView) view.findViewById(R.id.hot_article_listView));
        article_listView.setOnItemClickListener(this);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 101:
                    articleList = ((List<HotArticleData>) msg.obj);
                    hotArticleAdapter = new HotArticleAdapter(articleList,getContext(),R.layout.item_hot_article);
                    article_listView.setAdapter(hotArticleAdapter);
                    refreshListView();
                    break;
                case 201:
                    hotArticleAdapter.clear();
                    ApplicationHttpUtils.sendData(HttpRequest.HttpMethod.GET, articleUrl, null, new ApplicationStringCallBack(handler, 101));
                    hotArticleAdapter.notifyDataSetChanged();
                    article_listView.onRefreshComplete();
                    break;
            }
        }
    };

    private void refreshListView() {
        article_listView.setMode(PullToRefreshBase.Mode.BOTH);
        article_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                handler.sendEmptyMessageDelayed(201, 3000);
            }

            /**
             * 上拉加载
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String url = articleList.get(position-1).getUrl();
        String urlStr = baseUrl+url;

        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url",urlStr);

        startActivity(intent);
    }
}




























