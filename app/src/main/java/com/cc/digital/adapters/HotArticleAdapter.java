package com.cc.digital.adapters;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.digital.R;
import com.cc.digital.entity.HotArticleData;
import com.cc.digital.utils.CommonUtils;
import com.cc.digital.utils.FileUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
public class HotArticleAdapter extends ApplicationMyBaseAdapter<HotArticleData> {
    private List<HotArticleData> list;
    private Context context;
    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig displayConfig;


    public HotArticleAdapter(List<HotArticleData> list, Context context, int resID) {
        super(list, context, resID);
        this.list = list;
        this.context = context;

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        FileUtils fileUtils=new FileUtils(context, "articleimages");
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

    public void setList(List<HotArticleData> list) {
        this.list = list;
    }

    @Override
    public void fillData(ApplicationViewHolder myViewHolder, int position) {
        ImageView iv_item_icon = ((ImageView) myViewHolder.findView(R.id.iv_item_icon));//条目里的图片
        TextView tv_article_title = ((TextView) myViewHolder.findView(R.id.tv_article_title));//条目里的标题
        TextView tv_article_content = ((TextView) myViewHolder.findView(R.id.tv_article_content));//条目里的内容
        ImageView iv_article_category = ((ImageView) myViewHolder.findView(R.id.iv_article_category));//条目里的分类（图片）
        TextView tv_article_category = ((TextView) myViewHolder.findView(R.id.tv_article_category));//条目里的分类（iOS、Android）
        TextView tv_article_time = ((TextView) myViewHolder.findView(R.id.tv_article_time));//条目里的时间
        TextView tv_article_viewCount = ((TextView) myViewHolder.findView(R.id.tv_article_viewCount));//该条目评论的数量

        String picUrl = list.get(position).getPic_url();
        bitmapUtils.display(iv_item_icon, picUrl, displayConfig);

        tv_article_title.setText(list.get(position).getTitle());
        tv_article_content.setText(list.get(position).getSummary());

        String categroy = list.get(position).getArticleFields().getCatname();
        tv_article_category.setText(categroy);

        switch (categroy){
            case "周边":
                iv_article_category.setImageResource(R.mipmap.category_mouse_small);
                break;
            case "手机":
                iv_article_category.setImageResource(R.mipmap.category_phone_small);
                break;
            case "文具":
                iv_article_category.setImageResource(R.mipmap.category_pen_small);
                break;
            case "生活":
                iv_article_category.setImageResource(R.mipmap.category_home_small);
                break;
            default:
                iv_article_category.setImageResource(R.mipmap.category_notebook_small);
                break;
        }

        String timeStr = list.get(position).getArticleFields().getDateline();
        String tvTime = CommonUtils.getStringTime(timeStr);
        tv_article_time.setText(tvTime);

        tv_article_viewCount.setText(list.get(position).getArticleFields().getViewnum());
    }
}


























