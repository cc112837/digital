package com.cc.digital.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by Administrator on 2015/11/11.
 */
public class ApplicationViewHolder {
    private View contentView;//返回给 listview 的 view

    public ApplicationViewHolder(Context context,int resID){
        contentView = LayoutInflater.from(context).inflate(resID,null);//加载布局生成view
        contentView.setTag(this);//给 view 设置当前 viewholder 为 tag
    }

    public View getContentView() {
        return contentView;
    }

    /**
     *
     * @param contentView listview的复用view
     * @param context 上下文对象
     * @param resID  布局文件的id
     * @return
     */
    public static ApplicationViewHolder getViewHolder(View contentView,Context context,int resID){
        ApplicationViewHolder myViewHolder = null;
        if (contentView == null) {
            myViewHolder = new ApplicationViewHolder(context,resID);
        }else{
            myViewHolder = ((ApplicationViewHolder) contentView.getTag());
        }

        return myViewHolder;
    }

    public View findView(int id){
        return contentView.findViewById(id);
    }
}




























