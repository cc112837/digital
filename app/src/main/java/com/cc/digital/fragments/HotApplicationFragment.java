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
import com.cc.digital.adapters.HotApplicationAdapter;
import com.cc.digital.callback.ApplicationStringCallBack;
import com.cc.digital.entity.HotApplicationData;
import com.cc.digital.utils.ApplicationHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotApplicationFragment extends Fragment implements AdapterView.OnItemClickListener {
    private PullToRefreshListView application_listView;
    private HotApplicationAdapter hotApplicationAdapter;
    private List<HotApplicationData> applicationDataList;
    private MainActivity activity;
    private String applicationUrl = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&sortid=33&sortsearch=1&bid=375&limit=0_20&REQUESTCODE=39";
    private String baseUrl = "http://www.dgtle.com/";

    public HotApplicationFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot_application, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        ApplicationHttpUtils.sendData(HttpRequest.HttpMethod.GET, applicationUrl, null, new ApplicationStringCallBack(handler, 121));

        application_listView = ((PullToRefreshListView) view.findViewById(R.id.hot_application_listView));
        application_listView.setOnItemClickListener(this);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 121:
                    applicationDataList = ((List<HotApplicationData>) msg.obj);
                    hotApplicationAdapter = new HotApplicationAdapter(applicationDataList,getContext(),R.layout.item_hot_application);
                    application_listView.setAdapter(hotApplicationAdapter);
                    refreshListView();
                    break;
                case 411:
                    hotApplicationAdapter.clear();
                    ApplicationHttpUtils.sendData(HttpRequest.HttpMethod.GET, applicationUrl, null, new ApplicationStringCallBack(handler, 121));
                    hotApplicationAdapter.notifyDataSetChanged();
                    application_listView.onRefreshComplete();
                    break;
            }
        }
    };

    private void refreshListView() {
        application_listView.setMode(PullToRefreshBase.Mode.BOTH);
        application_listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                handler.sendEmptyMessageDelayed(411, 3000);
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
        String url = applicationDataList.get(position-1).getDiy_data().getUrl();
        String urlStr = baseUrl+url;

        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url",urlStr);

        startActivity(intent);
    }
}
