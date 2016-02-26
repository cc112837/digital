package com.cc.digital.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.digital.R;
import com.cc.digital.entity.HomeContent;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
public class MyHomePullAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<HomeContent> homelist;

    public MyHomePullAdapter(Context context, List<HomeContent> homelist) {
        this.context = context;
        this.homelist = homelist;
        inflater = LayoutInflater.from(context);
    }

    public void setHomelist(List<HomeContent> homelist) {
        this.homelist = homelist;
    }
    public void clear(){
        homelist.clear();
    }

    @Override
    public int getCount() {
        if (homelist != null) {
            return homelist.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return homelist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        final ViewHolder vh;
        if (convertView == null) {
            vh = new ViewHolder();
            view = inflater.inflate(R.layout.listpull_item, null);
            vh.iv_homebackheadid = ((ImageView) view.findViewById(R.id.iv_homebackheadid));
            vh.tv_hometitleid = (TextView) view.findViewById(R.id.tv_hometitleid);
            vh.tv_homecontentid = (TextView) view.findViewById(R.id.tv_homecontentid);
            vh.iv_typeid = ((ImageView) view.findViewById(R.id.iv_typeid));
            vh.tv_hometypeid = ((TextView) view.findViewById(R.id.tv_hometypeid));
            vh.tv_hometimeid = ((TextView) view.findViewById(R.id.tv_hometimeid));
            vh.tv_homecomcountid = ((TextView) view.findViewById(R.id.tv_homecomcountid));
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.display(vh.iv_homebackheadid, homelist.get(position).getPic_url());
        vh.tv_hometitleid.setText(homelist.get(position).getTitle());
        vh.tv_homecontentid.setText(homelist.get(position).getSummary());
        String dateline = homelist.get(position).getDateline();
        long l = Long.parseLong(dateline);
        long l1 = System.currentTimeMillis()/1000;
        long c=l1-l;
        if(c/60<60){
            vh.tv_hometimeid.setText(c/60+"分钟前");
        }
         else if(c/60/60<24){
            vh.tv_hometimeid.setText(c/60/60+"小时前");
        }
        else if(c/60/60/24<60){
            vh.tv_hometimeid.setText(c/60/60/24+"天前");
        }
        vh.tv_homecomcountid.setText(homelist.get(position).getCommentnum());
        int i = Integer.parseInt(homelist.get(position).getFavtimes());
        switch (i/10) {
            case 1:
                vh.iv_typeid.setImageResource(R.mipmap.category_mouse_small);
                vh.tv_hometypeid.setText("周边");
                break;
            case 2:
                vh.iv_typeid.setImageResource(R.mipmap.category_phone_small);
                vh.tv_hometypeid.setText("手机");
                break;
            case 3:
                vh.iv_typeid.setImageResource(R.mipmap.category_news_small);
                vh.tv_hometypeid.setText("资讯");
                break;
            case 4:
                vh.iv_typeid.setImageResource(R.mipmap.category_home_small);
                vh.tv_hometypeid.setText("活动");
                break;
            case 5:
                vh.iv_typeid.setImageResource(R.mipmap.category_joypad_small);
                vh.tv_hometypeid.setText("玩物");
                break;
            case 6:
                vh.iv_typeid.setImageResource(R.mipmap.category_apps_small);
                vh.tv_hometypeid.setText("应用");
                break;
            case 7:
                vh.iv_typeid.setImageResource(R.mipmap.category_ipod_small);
                vh.tv_hometypeid.setText("数码");
                break;
            case 8:
                vh.iv_typeid.setImageResource(R.mipmap.category_speaker_small);
                vh.tv_hometypeid.setText("影音");
                break;
            case 9:
                vh.iv_typeid.setImageResource(R.mipmap.category_notebook_small);
                vh.tv_hometypeid.setText("电脑");
                break;

        }
        return view;
    }

    private class ViewHolder {
        private ImageView iv_homebackheadid;
        private TextView tv_hometitleid;
        private TextView tv_homecontentid;
        private ImageView iv_typeid;
        private TextView tv_hometypeid;
        private TextView tv_hometimeid;
        private TextView tv_homecomcountid;
    }
}
