package com.cc.digital.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotViewPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> fragmentList;

    public HotViewPagerAdapter(FragmentManager fm,List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        int ret = 0;
        if (fragmentList != null){
            ret = fragmentList.size();
        }
        return ret;
    }
}
