package com.cc.digital.adapters;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.digital.R;
import com.cc.digital.entity.HotApplicationData;
import com.cc.digital.utils.FileUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotApplicationAdapter extends ApplicationMyBaseAdapter<HotApplicationData>{
    private List<HotApplicationData> list;
    private Context context;
    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig displayConfig;

    public HotApplicationAdapter(List<HotApplicationData> list, Context context, int resID) {
        super(list, context, resID);
        this.list = list;
        this.context = context;

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        FileUtils fileUtils=new FileUtils(context, "hotimages");
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

    public void setList(List<HotApplicationData> list) {
        this.list = list;
    }

    @Override
    public void fillData(ApplicationViewHolder myViewHolder, int position) {
        ImageView iv_app_icon = ((ImageView) myViewHolder.findView(R.id.iv_app_icon));
        TextView tv_title = ((TextView) myViewHolder.findView(R.id.tv_title));

        String picUrl = list.get(position).getDiy_data().getPicurl();
        bitmapUtils.display(iv_app_icon,picUrl,displayConfig);

        tv_title.setText(list.get(position).getSubject());
    }
}
