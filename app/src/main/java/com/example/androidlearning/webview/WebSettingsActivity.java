package com.example.androidlearning.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidlearning.R;

public class WebSettingsActivity extends AppCompatActivity {

    private static final String URL_JS = "http://www.imooc.com";
    private static final String URL_ZOOM = "https://www.flvcd.com/index.htm";
    private static final String URL_CACHE = "https://www.baidu.com";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_settings);

        webView = findViewById(R.id.web_view);
        webView.setWebViewClient(new WebViewClient());

        WebSettings webSettings = webView.getSettings();
        //设置允许js代码
//        webView.loadUrl(URL_JS);//测试JS代码
        webSettings.setJavaScriptEnabled(true);
        //是否支持缩放
//        webView.loadUrl(URL_ZOOM);//测试缩放代码
//        webSettings.setSupportZoom(true);
//        //设置内置的缩放控件,默认为false，意思是网页端不主动设置网页缩放时安卓端不会主动允许缩放的功能
//        webSettings.setBuiltInZoomControls(true);
//        //是否隐藏原生的缩放控件，默认为false，安卓的原生缩放控件默认的情况下是隐藏的
//        webSettings.setDisplayZoomControls(true);
        //设置缓存策略
        webView.loadUrl(URL_CACHE);//测试缩放代码
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ONLY);
//        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);//httpServer默认缓存策略是10s
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView.clearCache(true);//清理内存和文件中的所有缓存
    }
}