package com.example.androidlearning.webview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

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

public class WebViewOldActivity extends AppCompatActivity {

    private WebView mWvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_old);
        mWvMain=(WebView)findViewById(R.id.wv);
        //加载本地HTML
        //mWvMain.loadUrl("file:///android_asset/test.html");
        //加载网络URL
        //设置支持js
        mWvMain.getSettings().setJavaScriptEnabled(true);
        //设置打开网页在APP内而不是默认的浏览器
        mWvMain.setWebViewClient(new MyWebViewClient());
        //设置APP标题为网页标题，设置加载进度条
        mWvMain.setWebChromeClient(new myWebChromeClient());
        //mWvMain.evaluateJavascript();
        //mWvMain.addJavascriptInterface();
        mWvMain.loadUrl("https://m.baidu.com");
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
            Log.d("webView","onPageStarted...");
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("webView","onPageFinished...");
            //mWvMain.loadUrl("javascript:alert('hello')");
            mWvMain.evaluateJavascript("javascript:alert('hello')",null);
        }
    }

    class myWebChromeClient extends WebChromeClient{
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
        if(keyCode == KeyEvent.KEYCODE_BACK && mWvMain.canGoBack()){
            mWvMain.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}