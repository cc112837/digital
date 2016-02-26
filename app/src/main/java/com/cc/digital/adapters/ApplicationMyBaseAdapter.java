package com.cc.digital.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 自定义BaseAdapter
 */
public abstract class ApplicationMyBaseAdapter<T> extends BaseAdapter{
    private List<T> list;
    private Context context;
    private int resID;

    public ApplicationMyBaseAdapter(List<T> list, Context context, int resID) {
        this.list = list;
        this.context = context;
        this.resID = resID;
    }

    @Override
    public int getCount() {
        if (list != null){
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ApplicationViewHolder myViewHolder = ApplicationViewHolder.getViewHolder(convertView,context,resID);
        fillData(myViewHolder,position);

        return myViewHolder.getContentView();
    }

    public abstract  void fillData(ApplicationViewHolder myViewHolder,int position);
}
