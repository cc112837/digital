package com.cc.digital.adapters;

import android.content.Context;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.digital.R;
import com.cc.digital.entity.ApplicationListViewData;
import com.cc.digital.utils.CommonUtils;
import com.cc.digital.utils.FileUtils;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapCommonUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;

import java.util.List;

/**
 * 应用界面listview的适配器
 */
public class ApplicationListViewAdapter extends ApplicationMyBaseAdapter<ApplicationListViewData> {
    private List<ApplicationListViewData> list;
    private Context context;
    private BitmapUtils bitmapUtils;
    private BitmapDisplayConfig displayConfig;

    public ApplicationListViewAdapter(List<ApplicationListViewData> list, Context context, int resID) {
        super(list, context, resID);
        this.list = list;
        this.context = context;

        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        FileUtils fileUtils=new FileUtils(context, "listviewimages");
        bitmapUtils = new BitmapUtils(context,fileUtils.getCacheDir(),cacheSize);
        displayConfig = new BitmapDisplayConfig();
        displayConfig.setBitmapMaxSize(BitmapCommonUtils.getScreenSize(context));
        AlphaAnimation animation = new AlphaAnimation(0.1f,1.0f);
        animation.setDuration(500);
        displayConfig.setAnimation(animation);
    }

    public void addList(List<ApplicationListViewData> list) {
        this.list.addAll(list);//追加数据
    }

    public void clear(){
        list.clear();
    }

    public void setList(List<ApplicationListViewData> list) {
        this.list = list;
    }

    @Override
    public void fillData(ApplicationViewHolder myViewHolder, int position) {
        ImageView iv_item_icon = ((ImageView) myViewHolder.findView(R.id.iv_item_icon));//条目里的图片
        TextView tv_application_title = ((TextView) myViewHolder.findView(R.id.tv_application_title));//条目里的标题
        TextView tv_application_content = ((TextView) myViewHolder.findView(R.id.tv_application_content));//条目里的内容
        ImageView iv_application_category = ((ImageView) myViewHolder.findView(R.id.iv_application_category));//条目里的分类（图片）
        TextView tv_application_category = ((TextView) myViewHolder.findView(R.id.tv_application_category));//条目里的分类（iOS、Android）
        TextView tv_application_time = ((TextView) myViewHolder.findView(R.id.tv_application_time));//条目里的时间
        TextView tv_application_commentCount = ((TextView) myViewHolder.findView(R.id.tv_application_commentCount));//该条目评论的数量

        String picUrl = list.get(position).getThreadsort_data().getApp_pic();
        String[] subUrls1 = picUrl.split(";");
        String subUrls2 = subUrls1[3];
        String subUrl = subUrls2.substring(6, subUrls2.length() - 1);

        iv_item_icon.setTag(subUrl);
        bitmapUtils.display(iv_item_icon, subUrl,displayConfig);

        tv_application_title.setText(list.get(position).getSubject());
        tv_application_content.setText(list.get(position).getThreadsort_data().getApp_brief());

        String platform = list.get(position).getThreadsort_data().getApp_platform();
        switch (platform){
            case "1":
                iv_application_category.setImageResource(R.mipmap.iphone);
                break;
            case "0":
                iv_application_category.setImageResource(R.mipmap.android);
                break;
        }

        String type = list.get(position).getTypeid();
        if("1".equals(platform)){
            switch (type){
                case "200":
                    tv_application_category.setText("iOS"+"·"+"生活");
                    break;
                case "201":
                    tv_application_category.setText("iOS"+"·"+"效率");
                    break;
                case "202":
                    tv_application_category.setText("iOS"+"·"+"娱乐");
                    break;
                case "203":
                    tv_application_category.setText("iOS"+"·"+"社交");
                    break;
                case "204":
                    tv_application_category.setText("iOS"+"·"+"音乐");
                    break;
                case "205":
                    tv_application_category.setText("iOS"+"·"+"摄影");
                    break;
                case "206":
                    tv_application_category.setText("iOS"+"·"+"新闻");
                    break;
                case "207":
                    tv_application_category.setText("iOS"+"·"+"体育");
                    break;
                case "208":
                    tv_application_category.setText("iOS"+"·"+"美食");
                    break;
                case "209":
                    tv_application_category.setText("iOS"+"·"+"天气");
                    break;
                case "210":
                    tv_application_category.setText("iOS"+"·"+"健康");
                    break;
                case "212":
                    tv_application_category.setText("iOS"+"·"+"财务");
                    break;
                case "213":
                    tv_application_category.setText("iOS"+"·"+"工具");
                    break;
                case "300":
                    tv_application_category.setText("iOS"+"·"+"教育");
                    break;
                default:
                    tv_application_category.setText("iOS"+"·"+"其他");
                    break;

            }
        }else{
            switch (type){
                case "200":
                    tv_application_category.setText("Android"+"·"+"生活");
                    break;
                case "201":
                    tv_application_category.setText("Android"+"·"+"效率");
                    break;
                case "202":
                    tv_application_category.setText("Android"+"·"+"娱乐");
                    break;
                case "203":
                    tv_application_category.setText("Android"+"·"+"社交");
                    break;
                case "204":
                    tv_application_category.setText("Android"+"·"+"音乐");
                    break;
                case "205":
                    tv_application_category.setText("Android"+"·"+"摄影");
                    break;
                case "206":
                    tv_application_category.setText("Android"+"·"+"新闻");
                    break;
                case "207":
                    tv_application_category.setText("Android"+"·"+"体育");
                    break;
                case "208":
                    tv_application_category.setText("Android"+"·"+"美食");
                    break;
                case "209":
                    tv_application_category.setText("Android"+"·"+"天气");
                    break;
                case "210":
                    tv_application_category.setText("Android"+"·"+"健康");
                    break;
                case "212":
                    tv_application_category.setText("Android"+"·"+"财务");
                    break;
                case "213":
                    tv_application_category.setText("Android"+"·"+"工具");
                    break;
                case "300":
                    tv_application_category.setText("Android"+"·"+"教育");
                    break;
                default:
                    tv_application_category.setText("Android"+"·"+"其他");
                    break;

            }
        }

        String timeStr = list.get(position).getDateline();
        String tvTime = CommonUtils.getStringTime(timeStr);
        tv_application_time.setText(tvTime);

        tv_application_commentCount.setText(list.get(position).getReplies());
    }



}


























