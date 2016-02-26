package com.cc.digital.fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.digital.R;
import com.cc.digital.entity.Specific;
import com.cc.digital.utils.MyHomeHttpUtils;
import com.lidroid.xutils.BitmapUtils;


public class HomeSpecificFragment extends Fragment {
private Specific  specific;
    private String uri;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 7:
                    specific=(Specific) msg.obj;
                    BitmapUtils bitmapUtils=new BitmapUtils(getContext());
                    bitmapUtils.display(iv_specificid, specific.getPic());
                    tv_authorid.setText(specific.getAuthor());
                    dateline.setText(specific.getDateline());
                    tv_specontent.setText(specific.getTitle());
                    WebSettings webSettings= wb_disid.getSettings();
                    webSettings.setUseWideViewPort(true);
                    webSettings.setLoadWithOverviewMode(true);
                    webSettings.setBuiltInZoomControls(true);
                    webSettings.setTextZoom(300);
                    wb_disid.loadData(specific.getContent(), "text/html; charset=utf-8", "utf-8");

            }
        }
    };
    private ImageView iv_specificid;
    private TextView tv_authorid;
    private TextView dateline;
    private TextView tv_specontent;
    private String aid;
   private WebView wb_disid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_specific, container, false);
        init(view);
        return view;
    }

    private void init(View view) {

        iv_specificid = ((ImageView) view.findViewById(R.id.iv_specificid));
        tv_authorid = (TextView) view.findViewById(R.id.tv_authorid);
        dateline = (TextView) view.findViewById(R.id.dateline);
        tv_specontent = (TextView) view.findViewById(R.id.tv_specontent);
        wb_disid = (WebView) view.findViewById(R.id.wb_disid);
        ImageView  iv_backguideid=(ImageView) view.findViewById(R.id.iv_backguideid);
        iv_backguideid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });

        Bundle arguments = getArguments();
        if(arguments!=null){
            aid = arguments.getString("aid");
            MyHomeHttpUtils.handData(handler, 7, aid, getContext());
        }

    }


}
