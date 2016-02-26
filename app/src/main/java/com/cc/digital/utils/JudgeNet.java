package com.cc.digital.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2015/11/13.
 */
public class JudgeNet {
    public static  boolean judgeCon(Context context){
        ConnectivityManager connectivityManager= ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));

        NetworkInfo infos=connectivityManager.getActiveNetworkInfo();
        if(infos!=null){
            boolean b = infos.isConnected() || infos.isAvailable();
            return b;
        }
        return false;
    }
}
