package com.cc.digital;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cc.digital.adapters.CommunityItemAdapter;
import com.cc.digital.callback.MyStringCallBack;
import com.cc.digital.entity.CommunityItem1;
import com.cc.digital.fragments.ChangeFragmentHelper;
import com.cc.digital.fragments.community.CommunityItemFragment;
import com.cc.digital.mylayout.RefreshLayout;
import com.cc.digital.utils.CommunityHttpUtils;
import com.cc.digital.utils.MySildeOnTouchListenew;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

public class CommunityItem extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        RefreshLayout.OnLoadListener, View.OnClickListener, AdapterView.OnItemClickListener {
    private List<CommunityItem1.ReturnDataEntity.ThreadlistEntity> threadlistEntities = new ArrayList<>();
    private ListView listView;
    private CommunityItemAdapter communityItemAdapter;
    private HttpHandler httpHandler;
    private List<CommunityItem1.ReturnDataEntity.ThreadlistEntity> list;

    private RefreshLayout swipeLayout;

    private CommunityItemFragment communityItemFragment;

    private String title;
    private String urlStr;
    private int page = 0;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 5001:
                    CommunityItem1 communityItem1 = (CommunityItem1) msg.obj;
                    list = new ArrayList<>();
                    list.addAll(communityItem1.getReturnData().getThreadlist());
                    threadlistEntities.addAll(list);
                    list.clear();
                    communityItemAdapter.notifyDataSetChanged();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_item);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        urlStr = intent.getStringExtra("urlStr");

        initView();

        setData();

        setListener();
        swipeLayout.post(new Thread(new Runnable() {

            @Override
            public void run() {
                swipeLayout.setRefreshing(true);
            }
        }));
        onRefresh();
    }

    private void initView(){
        RelativeLayout community_item_container=(RelativeLayout) findViewById(R.id.community_item_container);
        swipeLayout = (RefreshLayout) findViewById(R.id.swipe_container);
        TextView community_one_title = (TextView) findViewById(R.id.community_one_title);
        community_one_title.setText(title);
        ImageView community_one_back = (ImageView) findViewById(R.id.community_one_back);
        community_one_back.setOnClickListener(this);
        community_item_container.setOnTouchListener(new MySildeOnTouchListenew(this));
    }

    /**
     * 添加数据
     */
    private void setData() {

        listView = (ListView) findViewById(R.id.community_list);
        listView.setOnItemClickListener(this);
        communityItemAdapter = new CommunityItemAdapter(threadlistEntities, this);
        listView.setAdapter(communityItemAdapter);
    }

    /**
     * 设置监听
     */
    private void setListener() {
        swipeLayout.setOnRefreshListener(this);
        swipeLayout.setOnLoadListener(this);
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        swipeLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                // 更新数据
                // 更新完后调用该方法结束刷新

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        page = 1;
                        String url = urlStr + page;
                        httpHandler = CommunityHttpUtils.sendDate(HttpRequest.HttpMethod.GET, url, null, new MyStringCallBack(new CommunityItem1(), handler, 5001));
                    }
                }).start();

                threadlistEntities.clear();
                communityItemAdapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        }, 5000);

    }

    /**
     * 加载更多
     */
    @Override
    public void onLoad() {
        swipeLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        page++;
                        String url = urlStr + page;
                        httpHandler = CommunityHttpUtils.sendDate(HttpRequest.HttpMethod.GET, url, null, new MyStringCallBack(new CommunityItem1(), handler, 5001));
                    }
                }).start();
                // 更新数据
                // 更新完后调用该方法结束刷新
                swipeLayout.setLoading(false);
                communityItemAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.community_one_back:
                finish();
                break;
        }
    }

    public void changeFragment(ChangeFragmentHelper helper) {

        //获取需要跳转的Fragment
        Fragment targetFragment = helper.getTargetFragment();
        //获取是否清空回退栈
        boolean clearStackBack = helper.isClearStackBack();
        //获取是否加入回退栈
        String targetFragmentTag = helper.getTargetFragmentTag();
        //获取Bundle
        Bundle bundle = helper.getBundle();
        //是否给Fragment传值
        if (bundle != null) {
            targetFragment.setArguments(bundle);
        }

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = manager.beginTransaction();

        if (targetFragment != null) {
            fragmentTransaction.replace(R.id.community_item_container,targetFragment);
        }

        //是否添加到回退栈
        if (targetFragmentTag != null) {
            fragmentTransaction.addToBackStack(targetFragmentTag);
        }

        //是否清空回退栈
        if(clearStackBack){
            manager.popBackStack(null,FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        fragmentTransaction.commit();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(position);
        communityItemFragment = new CommunityItemFragment(threadlistEntities.get(position).getTid());
//        getSupportFragmentManager().popBackStack();
//        getSupportFragmentManager().beginTransaction().replace(R.id.community_item_container,communityItemFragment).commit();
        ChangeFragmentHelper helper = new ChangeFragmentHelper();
        helper.setTargetFragmentTag("communityItemFragment");
        helper.setTargetFragment(communityItemFragment);
        helper.setIsClearStackBack(true);
        changeFragment(helper);
    }
}
