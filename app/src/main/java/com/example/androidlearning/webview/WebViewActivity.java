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

        findViewById(R.id.btn_base).setOnClickListener(this);
        findViewById(R.id.btn_goto).setOnClickListener(this);
        findViewById(R.id.btn_state).setOnClickListener(this);
        findViewById(R.id.btn_settings).setOnClickListener(this);
        findViewById(R.id.btn_web_client).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_base: {
                Intent intent = new Intent(WebViewActivity.this, LoadWebViewActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_goto: {
                Intent intent = new Intent(WebViewActivity.this, GoWebViewActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_state: {
                Intent intent = new Intent(WebViewActivity.this, StateWebViewActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_settings: {
                Intent intent = new Intent(WebViewActivity.this, WebSettingsActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_web_client: {
                Intent intent = new Intent(WebViewActivity.this, WebClientActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}