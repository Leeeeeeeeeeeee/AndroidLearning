package com.example.androidlearning.webview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidlearning.R;

public class LoadWebViewActivity extends AppCompatActivity {
    private WebView mWvMain;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_web_view);
        //找到控件
        mWvMain = findViewById(R.id.wv);
        mWvMain = (WebView) findViewById(R.id.wv);
        //加载本地HTML
        //mWvMain.loadUrl("file:///android_asset/test.html");
//        mWvMain.loadUrl("file://" + Environment.getExternalStorageDirectory() + File.separator + "1.html");
        //加载静态网页
//        mWvMain.loadData("<h1>Hello WebView</h1>", "text/html", "utf-8");
        //baseUrl是基础路径，imgsrc会和基础路径做拼接
        mWvMain.loadDataWithBaseURL("http://www.baidu.com", "<img src=\"/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png\"/> <a href=\"http://www.imooc.com\">toImooc.com</a>", "text/html", "utf-8", "www.baidu.com");
        //加载网页链接
//        mWvMain.loadUrl("https://m.baidu.com");
        //加载网络URL
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //启动debug模式
            WebView.setWebContentsDebuggingEnabled(true);
        }
        //设置支持js
        mWvMain.getSettings().setJavaScriptEnabled(true);
        //设置打开网页在APP内而不是默认的浏览器
        mWvMain.setWebViewClient(new MyWebViewClient());
        //设置APP标题为网页标题，设置加载进度条
        mWvMain.setWebChromeClient(new myWebChromeClient());
        //mWvMain.evaluateJavascript();
        //mWvMain.addJavascriptInterface();
    }
    class MyWebViewClient extends WebViewClient {
        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            Log.d("webView", "onPageStarted...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("webView", "onPageFinished...");
            //mWvMain.loadUrl("javascript:alert('hello')");
            //使用js语句
            //mWvMain.evaluateJavascript("javascript:alert('hello')", null);
        }
    }

    class myWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            setTitle(title);
        }
    }

    //设置点击回退按钮返回网页上一页
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWvMain.canGoBack()) {
            mWvMain.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}