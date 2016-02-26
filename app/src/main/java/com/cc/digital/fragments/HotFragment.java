package com.cc.digital.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cc.digital.MainActivity;
import com.cc.digital.R;
import com.cc.digital.adapters.HotViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment implements TabLayout.OnTabSelectedListener {
    private ViewPager vp_hot_viewPager;
    private ImageView iv_back;
    private MainActivity activity;

    public HotFragment() {
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_hot, container, false);

        initView(view);
        return view;
    }

    private void initView(View view) {
        TabLayout hot_tabLayout = ((TabLayout) view.findViewById(R.id.hot_tabLayout));
        iv_back = ((ImageView) view.findViewById(R.id.iv_back));
        iv_back.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                activity.open();
            }
        });

        TabLayout.Tab articleTab = hot_tabLayout.newTab();
        articleTab.setText("文章");
        hot_tabLayout.addTab(articleTab);

        TabLayout.Tab newsTab = hot_tabLayout.newTab();
        newsTab.setText("资讯");
        hot_tabLayout.addTab(newsTab);

        TabLayout.Tab applicationTab = hot_tabLayout.newTab();
        applicationTab.setText("应用");
        hot_tabLayout.addTab(applicationTab);

        vp_hot_viewPager = ((ViewPager) view.findViewById(R.id.vp_hot_viewPager));

        List<Fragment> fragmentList = new ArrayList<>();

        HotArticleFragment hotArticleFragment = new HotArticleFragment();
        fragmentList.add(hotArticleFragment);
        HotNewsFragment hotNewsFragment = new HotNewsFragment();
        fragmentList.add(hotNewsFragment);
        HotApplicationFragment hotApplicationFragment = new HotApplicationFragment();
        fragmentList.add(hotApplicationFragment);

        HotViewPagerAdapter viewPagerAdapter = new HotViewPagerAdapter(getChildFragmentManager(),fragmentList);
        vp_hot_viewPager.setAdapter(viewPagerAdapter);

        vp_hot_viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(hot_tabLayout));

        hot_tabLayout.setOnTabSelectedListener(this);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        vp_hot_viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}




























