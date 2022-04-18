package com.example.androidlearning.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidlearning.R;

public class WebViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        findViewById(R.id.btn_base);
        findViewById(R.id.btn_goto);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_base: {
                Intent intent = new Intent(WebViewActivity.this, BaseWebViewActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_goto: {
                Intent intent = new Intent(WebViewActivity.this, GoWebViewActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}