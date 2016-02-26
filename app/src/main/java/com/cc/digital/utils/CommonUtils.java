package com.cc.digital.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by Administrator on 2015/11/12.
 */
public class CommonUtils {

    /**
     * 将长整型秒值，转换成  yy-mm-dd hh:mm:ss
     * @param timeStr
     * @return
     */
    public static String getStringTime(String timeStr) {

        long l = Long.parseLong(timeStr);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTimeInMillis(l * 1000);
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(gc.getTime());

    }

    /**
     * 判断网络连接
     * @param context
     * @return
     */
    public static boolean judgeNetStatus(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netWorkInfo = manager.getActiveNetworkInfo();
        return netWorkInfo != null && netWorkInfo.isAvailable();
    }
    /**
     * 往本地存储文件
     * @param content
     * @param name
     * @param context
     */
    public static void write(String content, String name, Context context) {
        try {
            // 以追加的方式打开文件输出流
            FileOutputStream fos = context.openFileOutput(name+".txt",
                    context.MODE_PRIVATE);
            // 写入数据
            fos.write(content.getBytes());
            // 关闭文件输出流
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 从本地读取文件
     * @param fileName
     * @param context
     * @return
     */
    public  static String read(String fileName, Context context) {
        try {
            // 打开文件输入流
            FileInputStream fileInput = context.openFileInput(fileName+".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    fileInput));
            String str = null;
            StringBuilder stb = new StringBuilder();
            while ((str = br.readLine()) != null) {
                stb.append(str);
            }
            return stb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 删除指定的文件
     * @param fileName
     * @param context
     */
    public  void deleteFiles(String fileName,Context context) {
        try {
            // 获取data文件中的所有文件列表
            List<String> name = Arrays.asList(context.fileList());
            if (name.contains(fileName)) {
                context.deleteFile(fileName);
                Log.i("该文件成功删除！", fileName + "");
            } else
                Log.i("该文件输入错误或不存在!",fileName + "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
