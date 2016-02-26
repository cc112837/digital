package com.cc.digital;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {
    private WebView wv_application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        wv_application = ((WebView) findViewById(R.id.wv_application));

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Log.e("data", "%%%%%%%%%%%%%%%%%%%" + url);

        //为WebView设置一些参数
        WebSettings setting = wv_application.getSettings();

        setting.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
        setting.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
        setting.setSupportZoom(true);//是否可以缩放，默认true
        setting.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
        setting.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
        setting.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
        setting.setAppCacheEnabled(true);//是否使用缓存
        setting.setDomStorageEnabled(true);//DOM Storage
        setting.setCacheMode(WebSettings.LOAD_DEFAULT);

        wv_application.loadUrl(url);
        wv_application.setWebViewClient(new WebViewClient());
    }
}
