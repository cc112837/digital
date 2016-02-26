package com.cc.digital.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import com.cc.digital.MainActivity;
import com.cc.digital.R;
import com.cc.digital.adapters.MyNewsPullAdapter;
import com.cc.digital.entity.NewsContent;
import com.cc.digital.utils.MyHomeHttpUtils;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {
    private MainActivity activity;
    private PullToRefreshListView plv_newsid;
    private MyNewsPullAdapter adapter;
    private List<NewsContent> newslist;

    public NewsFragment() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = ((MainActivity) activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        MyHomeHttpUtils.handData(handler,8,getContext());
        ImageView iv_newsguideid=((ImageView) view.findViewById(R.id.iv_newsguideid));
        iv_newsguideid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.open();
            }
        });
     plv_newsid= ((PullToRefreshListView) view.findViewById(R.id.plv_newsid));
        plv_newsid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                 String aid = newslist.get(position-1).getPushedaid();
                NewsSpecificFragment newsSpecificFragment=new NewsSpecificFragment();
                Bundle bundle=new Bundle();
                bundle.putString("aid", aid);
                newsSpecificFragment.setArguments(bundle);
                ChangeFragmentHelper helper = new ChangeFragmentHelper();
                helper.setTargetFragment(newsSpecificFragment);
                helper.setTargetFragmentTag("newsSpecificFragment");
                activity.changeFragment(helper);
            }
        });
    }
private Handler handler=new Handler(){
    @Override
    public void handleMessage(Message msg) {
        switch (msg.what){
            case 8:
                newslist=(List<NewsContent>) msg.obj;
                adapter = new MyNewsPullAdapter(getContext(),newslist);
                plv_newsid.setAdapter(adapter);
                plv_newsid.setMode(PullToRefreshBase.Mode.BOTH);
                plv_newsid.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
                    @Override
                    public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                        Log.i("刷新开始啦", "down 下拉开始");
                        handler.sendEmptyMessageDelayed(10, 3000);
                    }
                    @Override
                    public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                        Log.i("刷新开始啦", "up 上拉开始");
                        handler.sendEmptyMessage(9);
                    }
                });
                break;
            case 9:
                MyHomeHttpUtils.handData(handler, 11, getContext());
                break;
            case 10:
                adapter.clear();
                MyHomeHttpUtils.handData(handler, 8, getContext());
                adapter.notifyDataSetChanged();
                plv_newsid.onRefreshComplete();
                break;
            case 11:
                List<NewsContent> addhomelist = (List<NewsContent>) msg.obj;
                adapter.setNewslist(addhomelist);
                adapter.notifyDataSetChanged();
                plv_newsid.onRefreshComplete();
                break;
        }
    }
};
}
