package com.example.androidlearning.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidlearning.R;

public class GoWebViewActivity extends AppCompatActivity {

    private WebView webView;
    private EditText mEtStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_web_view);
        webView = findViewById(R.id.web_view);
        mEtStep = findViewById(R.id.et_step);
    }

    public void onCanGoBack(View v) {
//        Toast.makeText(this, String.valueOf());
    }

    public void onGoBack(View v) {

    }

    public void onCanGoForward(View v) {

    }

    public void onGoForward(View v) {

    }

    public void onCanGoBackOrForward() {

    }

    public void onGoBackOrForward(View v) {

    }

    public void onClearHistory(View v) {

    }
}