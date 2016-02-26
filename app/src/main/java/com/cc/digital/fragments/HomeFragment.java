package com.cc.digital.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cc.digital.MainActivity;
import com.cc.digital.R;
import com.cc.digital.adapters.MyHomePagerAdapter;
import com.cc.digital.adapters.MyHomePullAdapter;
import com.cc.digital.entity.HomeContent;
import com.cc.digital.entity.PicCir;
import com.cc.digital.utils.MyHomeHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.lidroid.xutils.BitmapUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 首页
 */
public class HomeFragment extends Fragment {
    private MainActivity activity;
    private ViewPager vp_headid;//头部的viewpager控件
    private PullToRefreshListView plv_homeid;//获得listview的控件
    private MyHomePullAdapter pullAdapter;//listview的适配器
    private boolean isShowing = true;//判断当前图片轮询的viewpager是否正在显示
    private List<HomeContent> homelist;//listview的数据源
    private List<ImageView> imagelist;
    private List<PicCir> piclist;
    private MyHomePagerAdapter myHomePagerAdapter;
    private TextView tv_homedisplay;


    public HomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);//界面初始化
        return view;
    }

    /**
     * 界面控件的初始化
     *
     * @param view
     */
    private void init(View view) {

        MyHomeHttpUtils.handData(handler, 2, getContext());
        MyHomeHttpUtils.handData(handler, 1, getContext());
        plv_homeid = ((PullToRefreshListView) view.findViewById(R.id.plv_homeid));
        ListView listView = plv_homeid.getRefreshableView();
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.head_item, null);
        listView.addHeaderView(inflate);
        ImageView iv_backhomeid = ((ImageView) view.findViewById(R.id.iv_backhomeid));
        iv_backhomeid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("被点击了",">>>>>>>>>>>>>>>>");
                activity.open();
            }
        });
        vp_headid = ((ViewPager) inflate.findViewById(R.id.vp_headid));
        tv_homedisplay = ((TextView) inflate.findViewById(R.id.tv_homedisplay));
        plv_homeid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aid = homelist.get(position - 2).getAid();
                HomeSpecificFragment homeSpefragment = new HomeSpecificFragment();
                Bundle bundle = new Bundle();
                bundle.putString("aid", aid);
                homeSpefragment.setArguments(bundle);
                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(homeSpefragment);
                helper.setTargetFragmentTag("homeSpefragment");
                activity.changeFragment(helper);
            }
        });

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = ((MainActivity) activity);
    }

    /**
     * 头部viewpager的操作
     */
    private void aboutHeadImage() {
        imagelist = new ArrayList<>();
        for (int i = 0; i < piclist.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setMaxWidth(new DisplayMetrics().widthPixels);
            String pic_url = piclist.get(i).getPic_url();
            BitmapUtils bitmapUtils = new BitmapUtils(getContext());
            bitmapUtils.display(imageView, pic_url);
            imagelist.add(imageView);
        }
        myHomePagerAdapter = new MyHomePagerAdapter(imagelist);
        vp_headid.setAdapter(myHomePagerAdapter);
        vp_headid.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % piclist.size());
        vp_headid.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tv_homedisplay.setText(piclist.get(position % piclist.size()).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        handler.sendEmptyMessageDelayed(5, 3000);

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
        handler.sendEmptyMessageDelayed(5, 3000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    piclist = ((List<PicCir>) msg.obj);
                    aboutHeadImage();
                    break;
                case 2:
                    homelist = ((List<HomeContent>) msg.obj);
                    pullAdapter = new MyHomePullAdapter(getContext(), homelist);
                    plv_homeid.setAdapter(pullAdapter);
                    plv_homeid.setMode(PullToRefreshBase.Mode.BOTH);
                    plv_homeid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                        @Override
                        public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                            Log.i("刷新开始啦", "down 下拉开始");
                            handler.sendEmptyMessageDelayed(3, 3000);
                        }

                        @Override
                        public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                            Log.i("刷新开始啦", "up 上拉开始");
                            handler.sendEmptyMessage(6);
                        }
                    });
                    break;
                case 3:
                    pullAdapter.clear();
                    MyHomeHttpUtils.handData(handler, 2, getContext());
                    pullAdapter.notifyDataSetChanged();
                    plv_homeid.onRefreshComplete();
                    break;
                case 4:
                    List<HomeContent> addhomelist = (List<HomeContent>) msg.obj;
                    pullAdapter.setHomelist(addhomelist);
                    pullAdapter.notifyDataSetChanged();
                    plv_homeid.onRefreshComplete();
                    break;
                case 5:
                    if (isShowing) {
                        vp_headid.setCurrentItem(vp_headid.getCurrentItem() + 1);
                        sendEmptyMessageDelayed(5, 3000);
                    }
                    break;
                case 6:
                    MyHomeHttpUtils.handData(handler, 4, getContext());
                    break;
            }
        }
    };


}
