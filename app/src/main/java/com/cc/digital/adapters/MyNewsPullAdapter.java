package com.cc.digital.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.cc.digital.R;
import com.cc.digital.entity.NewsContent;
import com.lidroid.xutils.BitmapUtils;

import java.util.List;

/**
 * Created by Administrator on 2015/11/11.
 */
public class MyNewsPullAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Context context;
    private List<NewsContent> newslist;

    public MyNewsPullAdapter(Context context, List<NewsContent> newslist) {
        this.context = context;
        this.newslist = newslist;
        inflater = LayoutInflater.from(context);
    }

    public void setNewslist(List<NewsContent> newslist) {
        this.newslist=newslist;
    }
    public void clear(){
        newslist.clear();
    }

    @Override
    public int getCount() {
        if (newslist != null) {
            return newslist.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return newslist.get(position);
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
            view = inflater.inflate(R.layout.news_list_item, null);
            vh.tv_newstitleid = (TextView) view.findViewById(R.id.tv_newstitleid);
            vh.tv_newscontentid = (TextView) view.findViewById(R.id.tv_newscotentid);
            vh.iv_newsid = ((ImageView) view.findViewById(R.id.iv_newsid));
            vh.tv_newstimeid = ((TextView) view.findViewById(R.id.tv_newstimeid));
            vh.tv_newscomcountid = ((TextView) view.findViewById(R.id.tv_newscomcountid));
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }
        BitmapUtils bitmapUtils = new BitmapUtils(context);
        bitmapUtils.display(vh.iv_newsid,newslist.get(position).getSubstring() );
        vh.tv_newstitleid.setText(newslist.get(position).getSubject());
        vh.tv_newscontentid.setText(newslist.get(position).getApp_brief());
        String dateline = newslist.get(position).getDateline();
        long l = Long.parseLong(dateline);
        long l1 = System.currentTimeMillis()/1000;
        long c=l1-l;
        if(c/60<60){
            vh.tv_newstimeid.setText(c/60+"分钟前");
        }
         else if(c/60/60<24){
            vh.tv_newstimeid.setText(c/60/60+"小时前");
        }
        else if(c/60/60/24<60){
            vh.tv_newstimeid.setText(c/60/60/24+"天前");
        }
        vh.tv_newscomcountid.setText(newslist.get(position).getReplies());
        return view;
    }

    private class ViewHolder {
        private ImageView iv_newsid;
        private TextView tv_newstimeid;
        private TextView tv_newscontentid;
        private TextView tv_newstitleid;
        private TextView tv_newscomcountid;
    }
}
