package com.cc.digital.adapters;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.digital.R;
import com.cc.digital.entity.HotNewsData;
import com.cc.digital.utils.FileUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotNewsAdapter extends ApplicationMyBaseAdapter<HotNewsData> {
    private List<HotNewsData> list;
    private Context context;
    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig displayConfig;

    public HotNewsAdapter(List<HotNewsData> list, Context context, int resID) {
        super(list, context, resID);
        this.list = list;
        this.context = context;

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        FileUtils fileUtils=new FileUtils(context, "newsimages");
        bitmapUtils = new BitmapUtils(context,fileUtils.getCacheDir(),cacheSize);
        displayConfig = new BitmapDisplayConfig();
        displayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
        AlphaAnimation animation = new AlphaAnimation(0.1f,1.0f);
        animation.setDuration(500);
        displayConfig.setAnimation(animation);
    }

    public void clear(){
        list.clear();
    }

    public void setList(List<HotNewsData> list) {
        this.list = list;
    }

    @Override
    public void fillData(ApplicationViewHolder myViewHolder, int position) {
        ImageView iv_author_icon = ((ImageView) myViewHolder.findView(R.id.iv_author_icon));
        TextView tv_title = ((TextView) myViewHolder.findView(R.id.tv_title));
        TextView tv_username = ((TextView) myViewHolder.findView(R.id.tv_username));
        TextView tv_forum = ((TextView) myViewHolder.findView(R.id.tv_forum));
        TextView tv_views = ((TextView) myViewHolder.findView(R.id.tv_views));

        String avaterUrl = list.get(position).getNewsFields().getAvatar();
        bitmapUtils.display(iv_author_icon, avaterUrl, displayConfig);

        tv_title.setText(list.get(position).getTitle());
        tv_username.setText(list.get(position).getNewsFields().getAuthor());
        tv_forum.setText(list.get(position).getNewsFields().getTypename());
        tv_views.setText(list.get(position).getNewsFields().getViews());
    }
}
