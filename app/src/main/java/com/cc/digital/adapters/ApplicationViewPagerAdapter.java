package com.cc.digital.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cc.digital.WebViewActivity;

import java.util.List;

/**
 *  应用界面的ViewPager（图片轮播）的适配器
 */
public class ApplicationViewPagerAdapter extends PagerAdapter{
    private List<ImageView> list;
    private Activity activity;

    public ApplicationViewPagerAdapter(List<ImageView> list,Activity activity) {
        this.list = list;
        this.activity = activity;
    }


    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final ImageView view = list.get(position%list.size());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = view.getTag().toString();
                String urlStr = "http://www.dgtle.com/"+url;
                Intent intent = new Intent(activity, WebViewActivity.class);
                intent.putExtra("url",urlStr);
                activity.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(list.get(position%list.size()));
    }
}
