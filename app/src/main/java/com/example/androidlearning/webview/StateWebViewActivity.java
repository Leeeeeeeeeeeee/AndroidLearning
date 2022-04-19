package com.example.androidlearning.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.androidlearning.R;

public class StateWebViewActivity extends AppCompatActivity {

    private static final String URL = "http://imgcache.qq.com/open/qcloud/video/act/player_tool/player.html";//这个地址不太能用，没有好的测试地址
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state_web_view);

        webView = findViewById(R.id.web_view);
        webView.loadUrl(URL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        webView.onPause();
//        webView.pauseTimers();
    }

    @Override
    protected void onResume() {
        super.onResume();
        webView.onResume();
//        webView.resumeTimers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }


}