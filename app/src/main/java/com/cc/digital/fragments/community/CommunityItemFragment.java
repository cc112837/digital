package com.cc.digital.fragments.community;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;

import com.cc.digital.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CommunityItemFragment extends Fragment {
    private String tid;
    private WebView webView;
    private String url;
    private ImageView community_two_fav;
    private ImageView community_two_good;

    public CommunityItemFragment() {
        // Required empty public constructor
    }

    public CommunityItemFragment(String tid) {
        this.tid = tid;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_community_item, container, false);

        init(view);
        return view;
    }

    private void init(View view) {
        community_two_fav = (ImageView) view.findViewById(R.id.community_two_fav);
        community_two_good = (ImageView) view.findViewById(R.id.community_two_good);
        webView = (WebView) view.findViewById(R.id.community_two_webview);
        url = "http://bbs.dgtle.com/forum.php?mod=viewthread&tid="+tid+"&mobile=yes";
        webView.loadUrl(url);
    }

}
