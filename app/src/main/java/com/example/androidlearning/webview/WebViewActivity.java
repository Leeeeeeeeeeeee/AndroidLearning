package com.example.androidlearning.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.androidlearning.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

//        webView = findViewById(R.id.web_view);
//        webView.loadUrl("http://www.baidu.com/");
//        webView.setWebViewClient(new WebViewClient());
    }
}