package com.example.androidlearning.webview;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.androidlearning.R;

public class WebClientActivity extends AppCompatActivity {

    private static final String TAG = "<WebClientActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_client);

        //如果不指定webClient，访问网页会自动使用系统浏览器
        //webClient可以监听网页的各种回调通知

        WebView webView = findViewById(R.id.web_view);
        webView.loadUrl("https://m.baidu.com");
        webView.setWebViewClient(new WebViewClient(){
            /**
             *
             * @param view
             * @param url
             * @return
             */
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                Log.d(TAG, "shouldInterceptRequest() called with: view = [" + view + "], url = [" + url + "]");
                return super.shouldInterceptRequest(view, url);
            }

            /**
             * 5.0以上才能调用，提供了更多的功能
             * @param view
             * @param request
             * @return
             */
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Nullable
            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
                Log.d(TAG, "shouldInterceptRequest() called with: view = [" + view + "], request url = [" + request.getUrl().toString() + "]");
                return super.shouldInterceptRequest(view, request);
            }

            /**
             *
             * @param view
             * @param url
             * @param favicon 网页图标
             */
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }
        });
    }
}