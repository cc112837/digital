package com.cc.digital.utils;

import android.content.Context;

import java.io.File;

/**
 * Created by Administrator on 2015/11/13.
 */
public class FileUtils {
    /** 缓存文件目录 */
    private File mCacheDir;
    public FileUtils(Context context, String cacheDir){
        if (android.os.Environment.getExternalStorageState().
                equals(android.os.Environment.MEDIA_MOUNTED))
            mCacheDir = new File(cacheDir);
        else
            mCacheDir = context.getCacheDir();// 如何获取系统内置的缓存存储路径
        if(!mCacheDir.exists())
            mCacheDir.mkdirs();
    }
    public String getCacheDir(){
        return mCacheDir.getAbsolutePath();
    }

}
