package com.cc.digital.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cc.digital.MainActivity;
import com.cc.digital.R;
import com.cc.digital.adapters.ApplicationListViewAdapter;
import com.cc.digital.adapters.ApplicationViewPagerAdapter;
import com.cc.digital.callback.ApplicationStringCallBack;
import com.cc.digital.entity.ApplicationListViewData;
import com.cc.digital.entity.ApplicationViewPagerData;
import com.cc.digital.utils.ApplicationHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.http.client.HttpRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用fragment界面
 */
public class ApplicationFragment extends Fragment {
    private ImageView iv_nav_back;//界面左上角图片，按下弹出侧滑菜单
    private ImageView iv_sifting_category;//界面右上角图片，按下弹出筛选分类界面
    private PullToRefreshListView listView;
    private ViewPager vp_application_head;//listview头部图片轮播
    private TextView tv_viewpager_title;//轮播图片的标题显示

    private ApplicationListViewAdapter listViewAdapter;//listview的适配器
    private ApplicationViewPagerAdapter viewPagerAdapter;//viewpager的适配器

    private List<ApplicationListViewData> listViewDataList;//listview数据源
    private List<ApplicationViewPagerData> viewPagerDataList;//viewpager数据源
    private List<ImageView> imageList;//轮播的图片集合

    private boolean isShowing = true;//当前轮播图片是否显示
    private int page = 0;//listview请求数据的页数

    private MainActivity activity;

    private String urlStr = "http://www.dgtle.com/";
    private String viewpagerUrl = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=portal&actions=diydata&bid=360";
    private String listviewUrl1 = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&sortid=33,38&sortsearch=1&App_platform=0&limit=";
    private String listviewUrl2 = "_20&fid=60&REQUESTCODE=5";
    private String listviewUrl = null;

    public ApplicationFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application, container, false);
        initView(view);

        return view;
    }

    /**
     * 初始化界面控件
     *
     * @param view
     */
    private void initView(View view) {
        //请求viewpager的数据
        ApplicationHttpUtils.sendData(HttpRequest.HttpMethod.GET, viewpagerUrl, null, new ApplicationStringCallBack(handler, 100));
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        listviewUrl = listviewUrl1 + page + "" + listviewUrl2;
        //请求listview的数据
        ApplicationHttpUtils.sendData(HttpRequest.HttpMethod.GET, listviewUrl, null, new ApplicationStringCallBack(handler, 200));

        listView = ((PullToRefreshListView) view.findViewById(R.id.application_listView));
        iv_nav_back = ((ImageView) view.findViewById(R.id.iv_nav_back));
        iv_nav_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.open();
            }
        });
        iv_sifting_category = ((ImageView) view.findViewById(R.id.iv_sifting_category));

        ListView applicationListView = listView.getRefreshableView();
        View headView = LayoutInflater.from(getContext()).inflate(R.layout.item_application_head, null);
        applicationListView.addHeaderView(headView);
        vp_application_head = ((ViewPager) headView.findViewById(R.id.vp_application_head));
        tv_viewpager_title = ((TextView) headView.findViewById(R.id.tv_viewpager_title));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }

    /**
     * 关于listview头部图片轮播的操作
     */
    private void aboutViewPager() {
        imageList = new ArrayList<>();
        for (int i = 0; i < viewPagerDataList.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setMaxWidth(new DisplayMetrics().widthPixels);
            String pic_url = viewPagerDataList.get(i).getPic_url();
            String url = viewPagerDataList.get(i).getUrl();
            imageView.setTag(url);
            BitmapUtils bitmapUtils = new BitmapUtils(getContext());
            bitmapUtils.display(imageView, pic_url);
            imageList.add(imageView);
        }

        viewPagerAdapter = new ApplicationViewPagerAdapter(imageList, getActivity());
        vp_application_head.setAdapter(viewPagerAdapter);
        vp_application_head.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % viewPagerDataList.size());
        vp_application_head.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                tv_viewpager_title.setText(viewPagerDataList.get(position % viewPagerDataList.size()).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        handler.sendEmptyMessageDelayed(500, 3000);
    }

    @Override
    public void onStop() {
        super.onStop();
        isShowing = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        isShowing = true;
        handler.sendEmptyMessageDelayed(500, 3000);
    }

    /**
     * listview的下拉刷新，上拉加载
     */
    private void refreshListView() {
        listView.setMode(PullToRefreshBase.Mode.BOTH);
        listView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {

            /**
             * 下拉刷新
             * @param refreshView
             */
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

                handler.sendEmptyMessageDelayed(300, 3000);
            }

            /**
             * 上拉加载
             * @param refreshView
             */
            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                handler.sendEmptyMessageDelayed(600, 3000);
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 100://请求viewpager轮播图片
                    viewPagerDataList = ((List<ApplicationViewPagerData>) msg.obj);
                    aboutViewPager();
                    break;
                case 200://请求listview的数据
                    listViewDataList = ((List<ApplicationListViewData>) msg.obj);
                    listViewAdapter = new ApplicationListViewAdapter(listViewDataList, activity, R.layout.item_application_listview);
                    listView.setAdapter(listViewAdapter);
                    refreshListView();
                    break;
                case 300://下拉刷新
                    page = 0;
                    listviewUrl = listviewUrl1 + page + "" + listviewUrl2;
                    listViewAdapter.clear();
                    //请求listview的数据
                    ApplicationHttpUtils.sendData(HttpRequest.HttpMethod.GET, listviewUrl, null, new ApplicationStringCallBack(handler, 200));

                    listViewAdapter.notifyDataSetChanged();
                    listView.onRefreshComplete();
                    break;
                case 400://上拉加载处理
                    List<ApplicationListViewData> addList = ((List<ApplicationListViewData>) msg.obj);
                    listViewAdapter.setList(addList);
                    listViewAdapter.notifyDataSetChanged();
                    listView.onRefreshComplete();
                    break;
                case 500://viewpager图片轮播操作
                    if (isShowing) {
                        vp_application_head.setCurrentItem(vp_application_head.getCurrentItem() + 1);
                        sendEmptyMessageDelayed(500, 3000);
                    }
                    break;
                case 600://上拉加载请求数据
                    page += 20;
                    listviewUrl = listviewUrl1 + page + "" + listviewUrl2;
                    ApplicationHttpUtils.sendData(HttpRequest.HttpMethod.GET, listviewUrl, null, new ApplicationStringCallBack(handler, 400));
                    break;
            }
        }
    };

}
