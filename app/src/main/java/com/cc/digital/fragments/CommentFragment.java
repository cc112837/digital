package com.cc.digital.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.digital.MainActivity;
import com.cc.digital.R;
import com.cc.digital.CommunityItem;
import com.cc.digital.entity.CommunityHome;
import com.cc.digital.utils.CommunityHttpUtils;
import com.lidroid.xutils.http.HttpHandler;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommentFragment extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private HttpHandler httpHandler;
    private CommunityHome communityHome;
    private String fragmentTag;
    private String urlStr;
    private FragmentManager fragmentManager;
    private MainActivity activity;


    private TextView community_weibazhuban_title;
    private TextView community_weibazhuban_count;
    private ImageView iv_back;

//    private TextView community_shuaishuaiweiba_title;
//    private TextView community_shuaishuaiweiba_count;

    private TextView community_weibarenzheng_title;
    private TextView community_weibarenzheng_count;

    private TextView community_weibaliangpin_title;
    private TextView community_weibaliangpin_count;

    private TextView community_jiujieweiba_title;
    private TextView community_jiujieweiba_count;

    private TextView community_zhanwujiaoliu_title;
    private TextView community_zhanwujiaoliu_count;

    private List<CommunityHome.ReturnDataEntity.ForumdataEntity.PengyuEntity.ForumsEntity> forums;

    public CommentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_comment, container, false);
        fragmentManager = getFragmentManager();
        init(fragmentView);

        httpHandler = CommunityHttpUtils.getData(handler, 5000);
        return fragmentView;
    }

    private void init(final View fragmentView) {
        View community_weibazhuban = fragmentView.findViewById(R.id.community_weibazhuban);
        community_weibazhuban.setOnClickListener(this);
        iv_back = (ImageView) fragmentView.findViewById(R.id.iv_back);
        iv_back.setOnClickListener(this);
        community_weibazhuban_title = (TextView) community_weibazhuban.findViewById(R.id.community_item_title);
        community_weibazhuban_count = (TextView) community_weibazhuban.findViewById(R.id.community_item_count);

//        View community_shuaishuaiweiba = fragmentView.findViewById(R.id.community_shuaishuaiweiba);
//        community_shuaishuaiweiba.setOnClickListener(this);
//        community_shuaishuaiweiba_title = (TextView) community_shuaishuaiweiba.findViewById(R.id.community_item_title);
//        community_shuaishuaiweiba_count = (TextView) community_shuaishuaiweiba.findViewById(R.id.community_item_count);

        View community_weibarenzheng = fragmentView.findViewById(R.id.community_weibarenzheng);
        community_weibarenzheng.setOnClickListener(this);
        community_weibarenzheng_title = (TextView) community_weibarenzheng.findViewById(R.id.community_item_title);
        community_weibarenzheng_count = (TextView) community_weibarenzheng.findViewById(R.id.community_item_count);

        View community_weibaliangpin = fragmentView.findViewById(R.id.community_weibaliangpin);
        community_weibaliangpin.setOnClickListener(this);
        community_weibaliangpin_title = (TextView) community_weibaliangpin.findViewById(R.id.community_item_title);
        community_weibaliangpin_count = (TextView) community_weibaliangpin.findViewById(R.id.community_item_count);

        View community_jiujieweiba = fragmentView.findViewById(R.id.community_jiujieweiba);
        community_jiujieweiba.setOnClickListener(this);
        community_jiujieweiba_title = (TextView) community_jiujieweiba.findViewById(R.id.community_item_title);
        community_jiujieweiba_count = (TextView) community_jiujieweiba.findViewById(R.id.community_item_count);

        View community_zhanwujiaoliu = fragmentView.findViewById(R.id.community_zhanwujiaoliu);
        community_zhanwujiaoliu.setOnClickListener(this);
        community_zhanwujiaoliu_title = (TextView) community_zhanwujiaoliu.findViewById(R.id.community_item_title);
        community_zhanwujiaoliu_count = (TextView) community_zhanwujiaoliu.findViewById(R.id.community_item_count);
//                community_weibazhuban_title.setText(forums.get(2).getName());
//                System.out.println(forums.get(2).getName());
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 5000:
                    communityHome = (CommunityHome) msg.obj;
                    forums = communityHome.getReturnData().getForumdata().getPengyu().getForums();

                    community_weibazhuban_title.setText(forums.get(0).getName());
                    community_weibazhuban_count.setText(forums.get(0).getPosts());

//                    community_shuaishuaiweiba_title.setText(forums.get(1).getName());
//                    community_shuaishuaiweiba_count.setText(forums.get(1).getPosts());

                    community_weibarenzheng_title.setText(forums.get(2).getName());
                    community_weibarenzheng_count.setText(forums.get(2).getPosts());

                    community_weibaliangpin_title.setText(forums.get(3).getName());
                    community_weibaliangpin_count.setText(forums.get(3).getPosts());

                    community_jiujieweiba_title.setText(forums.get(4).getName());
                    community_jiujieweiba_count.setText(forums.get(4).getPosts());

                    community_zhanwujiaoliu_title.setText(forums.get(5).getName());
                    community_zhanwujiaoliu_count.setText(forums.get(5).getPosts());
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity, CommunityItem.class);
        switch (v.getId()) {
            case R.id.community_weibazhuban:
                urlStr = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=forum&actions=forumdisplay&fid=2&typeid=&orderby=lastpost&page=";
                intent.putExtra("urlStr", urlStr);
                intent.putExtra("title", "尾巴主版");
                break;
//            case R.id.community_shuaishuaiweiba:
//                urlStr = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&sortsearch=1&sortid=27&attachdata=1&orderby=dateline&fid=46&REQUESTCODE=5&page=";
//                intent.putExtra("urlStr", urlStr);
//                intent.putExtra("title", "甩甩尾巴");
//                break;
            case R.id.community_weibarenzheng:
                urlStr = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=forum&actions=forumdisplay&fid=77&typeid=&orderby=lastpost&page=";
                intent.putExtra("urlStr", urlStr);
                intent.putExtra("title", "尾巴认证");
                break;
            case R.id.community_weibaliangpin:
                urlStr = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=forum&actions=forumdisplay&fid=36&typeid=&orderby=lastpost&page=";
                intent.putExtra("urlStr", urlStr);
                intent.putExtra("title", "尾巴良品");
                break;
            case R.id.community_jiujieweiba:
                urlStr = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=forum&actions=forumdisplay&fid=49&typeid=&orderby=lastpost&page=";
                intent.putExtra("urlStr", urlStr);
                intent.putExtra("title", "纠结尾巴");
                break;
            case R.id.community_zhanwujiaoliu:
                urlStr = "http://www.dgtle.com/api/dgtle_api/v1/api.php?charset=UTF8&dataform=json&swh=480x800&apikeys=DGTLECOM_APITEST1&modules=forum&actions=forumdisplay&fid=38&typeid=&orderby=lastpost&page=";
                intent.putExtra("urlStr", urlStr);
                intent.putExtra("title", "站务交流");
                break;

            case R.id.iv_back:
                activity.open();
        }
        startActivity(intent);
    }

    @Override
    public void onRefresh() {

        httpHandler = CommunityHttpUtils.getData(handler, 5000);
    }

    @Override
    public void onAttach(Activity activity) {
        this.activity = (MainActivity) activity;
        super.onAttach(activity);
    }
}
