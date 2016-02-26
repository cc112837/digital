package com.cc.digital.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.cc.digital.R;
import com.cc.digital.inter.Inter;


public class NewsSpecificFragment extends Fragment {

    private WebView wb_newsspeid;

    public NewsSpecificFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = inflater.inflate(R.layout.fragment_news_specific, container, false);
        init(inflate);
        return inflate;
    }

    private void init(View v) {
       ImageView iv_newsbackguideid=(ImageView) v.findViewById(R.id.iv_newsbackguideid);
        wb_newsspeid = (WebView) v.findViewById(R.id.wb_newsspeid);

        iv_newsbackguideid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        Bundle arguments = getArguments();
        if(arguments!=null){
            String aid = arguments.getString("aid");
            Log.i("aid的值是",aid);
            Log.i("xiangqingjiemian",Inter.newsbase+aid+"&mobile=yes");
            wb_newsspeid.loadUrl(Inter.newsbase + aid + "&mobile=yes");
        }

    }


}
