package com.cc.digital.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.digital.R;
import com.cc.digital.entity.CommunityItem1;

import java.util.List;

/**
 * Created by PENG on 2015/11/12 0012.
 */
public class CommunityItemAdapter extends BaseAdapter {
    private String typeid_0 = "全部";
    private String typeid_3 = "沙龙";
    private String typeid_4 = "平板";
    private String typeid_5 = "影音";
    private String typeid_7 = "文具";
    private String typeid_8 = "其它";
    private String typeid_17 = "笔电";
    private String typeid_18 = "手机";
    private String typeid_19 = "周边";
    private String typeid_20 = "数码";
    private String typeid_22 = "摄影";
    private String typeid_24 = "晒单";
    private String typeid_25 = "问题";
    private String typeid_26 = "建议";
    private String typeid_27 = "其他";
    private String typeid_30 = "问题";
    private String typeid_31 = "建议";
    private String typeid_32 = "交流";
    private String typeid_33 = "公告";
    private String typeid_34 = "生活";
    private String typeid_75 = "玩物";
    private String typeid_120 = "手机";
    private String typeid_121 = "平板";
    private String typeid_122 = "摄影";
    private String typeid_123 = "生活";
    private String typeid_124 = "玩物";
    private String typeid_125 = "文具";
    private String typeid_126 = "周边";
    private String typeid_127 = "数码";
    private String typeid_128 = "影音";
    private String typeid_129 = "笔电";
    private String typeid_130 = "游戏";
    private String typeid_132 = "公告";
    private String typeid_134 = "其他";
    private String typeid_135 = "应用";
    private String typeid_137 = "旅行";
    private String typeid_138 = "活动";
    private String typeid_261 = "客户端";
    private String typeid_262 = "账号";
    private String typeid_263 = "举报";
    private String typeid_299 = "公告";

    private String date;

    private List<CommunityItem1.ReturnDataEntity.ThreadlistEntity> threadlistEntities;
    private Context context;

    public CommunityItemAdapter(List<CommunityItem1.ReturnDataEntity.ThreadlistEntity> threadlistEntities, Context context) {
        this.threadlistEntities = threadlistEntities;
        this.context = context;
    }

    public void setThreadlistEntities(List<CommunityItem1.ReturnDataEntity.ThreadlistEntity> threadlistEntities) {
        this.threadlistEntities = threadlistEntities;
    }

    @Override
    public int getCount() {
        if (threadlistEntities.size() != 0) {
            return threadlistEntities.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return threadlistEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.community_item_two, null);
            viewHolder.user_photo = (ImageView) convertView.findViewById(R.id.user_photo);
            viewHolder.tv_community_item2_author = (TextView) convertView.findViewById(R.id.tv_community_item2_author);
            viewHolder.tv_community_item2_dateline = (TextView) convertView.findViewById(R.id.tv_community_item2_dateline);
            viewHolder.tv_community_item2_subject = (TextView) convertView.findViewById(R.id.tv_community_item2_subject);
            viewHolder.tv_community_item2_typeid = (TextView) convertView.findViewById(R.id.tv_community_item2_typeid);
            viewHolder.tv_community_item2_replies = (TextView) convertView.findViewById(R.id.tv_community_item2_replies);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        CommunityItem1.ReturnDataEntity.ThreadlistEntity threadlistEntity = (CommunityItem1.ReturnDataEntity.ThreadlistEntity) getItem(position);

        if (threadlistEntity != null) {
            date = threadlistEntity.getDateline();
//            System.out.println(s1);
            String[] s = date.split("\"");
//            System.out.println(s[0]);
            if (s.length != 1 ){
                date = s[1];
            }

            viewHolder.tv_community_item2_subject.setText(threadlistEntity.getSubject());
            viewHolder.tv_community_item2_dateline.setText(date);
            viewHolder.tv_community_item2_author.setText(threadlistEntity.getAuthor());
            viewHolder.tv_community_item2_replies.setText(threadlistEntity.getReplies());


            if (threadlistEntity.getTypeid().equals("3")) {
                viewHolder.user_photo.setImageResource(R.mipmap.shalong);
                viewHolder.tv_community_item2_typeid.setText(typeid_3);
            } else if (threadlistEntity.getTypeid().equals("4")) {
                viewHolder.user_photo.setImageResource(R.mipmap.pingban);
                viewHolder.tv_community_item2_typeid.setText(typeid_4);
            } else if (threadlistEntity.getTypeid().equals("5")) {
                viewHolder.user_photo.setImageResource(R.mipmap.yingyin);
                viewHolder.tv_community_item2_typeid.setText(typeid_5);
            } else if (threadlistEntity.getTypeid().equals("7")) {
                viewHolder.user_photo.setImageResource(R.mipmap.wenju);
                viewHolder.tv_community_item2_typeid.setText(typeid_7);
            } else if (threadlistEntity.getTypeid().equals("8")) {
                viewHolder.tv_community_item2_typeid.setText(typeid_8);
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
            } else if (threadlistEntity.getTypeid().equals("17")) {
                viewHolder.user_photo.setImageResource(R.mipmap.bidian);
                viewHolder.tv_community_item2_typeid.setText(typeid_17);
            } else if (threadlistEntity.getTypeid().equals("18")) {
                viewHolder.user_photo.setImageResource(R.mipmap.shouji);
                viewHolder.tv_community_item2_typeid.setText(typeid_18);
            } else if (threadlistEntity.getTypeid().equals("19")) {
                viewHolder.user_photo.setImageResource(R.mipmap.zhoubian);
                viewHolder.tv_community_item2_typeid.setText(typeid_19);
            } else if (threadlistEntity.getTypeid().equals("20")) {
                viewHolder.user_photo.setImageResource(R.mipmap.shuma);
                viewHolder.tv_community_item2_typeid.setText(typeid_20);
            } else if (threadlistEntity.getTypeid().equals("22")) {
                viewHolder.user_photo.setImageResource(R.mipmap.sheying);
                viewHolder.tv_community_item2_typeid.setText(typeid_22);
            } else if (threadlistEntity.getTypeid().equals("34")) {
                viewHolder.user_photo.setImageResource(R.mipmap.shenghuo);
                viewHolder.tv_community_item2_typeid.setText(typeid_34);
            } else if (threadlistEntity.getTypeid().equals("75")) {
                viewHolder.user_photo.setImageResource(R.mipmap.wanwu);
                viewHolder.tv_community_item2_typeid.setText(typeid_75);
            } else if (threadlistEntity.getTypeid().equals("135")) {
                viewHolder.user_photo.setImageResource(R.mipmap.yingyong);
                viewHolder.tv_community_item2_typeid.setText(typeid_135);
            } else if (threadlistEntity.getTypeid().equals("137")) {
                viewHolder.user_photo.setImageResource(R.mipmap.lvxing);
                viewHolder.tv_community_item2_typeid.setText(typeid_137);
            } else if (threadlistEntity.getTypeid().equals("138")) {
                viewHolder.user_photo.setImageResource(R.mipmap.huodong);
                viewHolder.tv_community_item2_typeid.setText(typeid_138);
            }else if(threadlistEntity.getTypeid().equals("0")){
                viewHolder.user_photo.setImageResource(R.mipmap.quanbu);
                viewHolder.tv_community_item2_typeid.setText(typeid_0);
            }else if(threadlistEntity.getTypeid().equals("24")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_24);
            }else if(threadlistEntity.getTypeid().equals("25")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_25);
            }else if(threadlistEntity.getTypeid().equals("26")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_26);
            }else if(threadlistEntity.getTypeid().equals("27")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_27);
            }else if(threadlistEntity.getTypeid().equals("299")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_299);
            }else if(threadlistEntity.getTypeid().equals("120")){
                viewHolder.user_photo.setImageResource(R.mipmap.shouji);
                viewHolder.tv_community_item2_typeid.setText(typeid_120);
            }else if(threadlistEntity.getTypeid().equals("121")){
                viewHolder.user_photo.setImageResource(R.mipmap.pingban);
                viewHolder.tv_community_item2_typeid.setText(typeid_121);
            }else if(threadlistEntity.getTypeid().equals("122")){
                viewHolder.user_photo.setImageResource(R.mipmap.sheying);
                viewHolder.tv_community_item2_typeid.setText(typeid_122);
            }else if(threadlistEntity.getTypeid().equals("123")){
                viewHolder.user_photo.setImageResource(R.mipmap.shenghuo);
                viewHolder.tv_community_item2_typeid.setText(typeid_123);
            }else if(threadlistEntity.getTypeid().equals("124")){
                viewHolder.user_photo.setImageResource(R.mipmap.wanwu);
                viewHolder.tv_community_item2_typeid.setText(typeid_124);
            }else if(threadlistEntity.getTypeid().equals("125")){
                viewHolder.user_photo.setImageResource(R.mipmap.wenju);
                viewHolder.tv_community_item2_typeid.setText(typeid_125);
            }else if(threadlistEntity.getTypeid().equals("126")){
                viewHolder.user_photo.setImageResource(R.mipmap.zhoubian);
                viewHolder.tv_community_item2_typeid.setText(typeid_126);
            }else if(threadlistEntity.getTypeid().equals("127")){
                viewHolder.user_photo.setImageResource(R.mipmap.shuma);
                viewHolder.tv_community_item2_typeid.setText(typeid_127);
            }else if(threadlistEntity.getTypeid().equals("128")){
                viewHolder.user_photo.setImageResource(R.mipmap.yingyin);
                viewHolder.tv_community_item2_typeid.setText(typeid_128);
            }else if(threadlistEntity.getTypeid().equals("129")){
                viewHolder.tv_community_item2_typeid.setText(typeid_129);
            }else if(threadlistEntity.getTypeid().equals("130")){
                viewHolder.user_photo.setImageResource(R.mipmap.youxi);
                viewHolder.tv_community_item2_typeid.setText(typeid_130);
            }else if(threadlistEntity.getTypeid().equals("132")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_132);
            }else if(threadlistEntity.getTypeid().equals("134")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_134);
            }else if(threadlistEntity.getTypeid().equals("30")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_30);
            }else if(threadlistEntity.getTypeid().equals("31")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_31);
            }else if(threadlistEntity.getTypeid().equals("32")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_32);
            }else if(threadlistEntity.getTypeid().equals("33")){
                viewHolder.user_photo.setImageResource(R.mipmap.qita);
                viewHolder.tv_community_item2_typeid.setText(typeid_33);
            }else if(threadlistEntity.getTypeid().equals("261")){
                viewHolder.tv_community_item2_typeid.setText(typeid_261);
            }else if(threadlistEntity.getTypeid().equals("262")){
                viewHolder.tv_community_item2_typeid.setText(typeid_262);
            }else if(threadlistEntity.getTypeid().equals("263")){
                viewHolder.tv_community_item2_typeid.setText(typeid_263);
            }
        }
        return convertView;
    }
    private static class ViewHolder {
        private ImageView user_photo;
        private TextView tv_community_item2_author;
        private TextView tv_community_item2_dateline;
        private TextView tv_community_item2_subject;
        private TextView tv_community_item2_typeid;
        private TextView tv_community_item2_replies;
    }
}
