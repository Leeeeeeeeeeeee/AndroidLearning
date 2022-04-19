package com.example.androidlearning.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.Toast;

import com.example.androidlearning.R;

public class GoWebViewActivity extends AppCompatActivity implements View.OnClickListener {

    private WebView webView;
    private EditText mEtStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go_web_view);
        webView = findViewById(R.id.web_view);
        webView.loadUrl("https://m.baidu.com");
        webView.setWebViewClient(new WebViewClient());
        mEtStep = findViewById(R.id.et_step);
        findViewById(R.id.btn_can_go_back).setOnClickListener(this);
        findViewById(R.id.btn_go_back).setOnClickListener(this);
        findViewById(R.id.btn_can_go_forward).setOnClickListener(this);
        findViewById(R.id.btn_go_forward).setOnClickListener(this);
        findViewById(R.id.btn_can_go_back_or_forward).setOnClickListener(this);
        findViewById(R.id.btn_go_back_or_forward).setOnClickListener(this);
        findViewById(R.id.btn_clear_history).setOnClickListener(this);
    }

    public void onCanGoBack() {
        Toast.makeText(this, String.valueOf(webView.canGoBack()), Toast.LENGTH_SHORT).show();
    }

    public void onGoBack() {
        webView.goBack();
    }

    public void onCanGoForward() {
        Toast.makeText(this, String.valueOf(webView.canGoForward()), Toast.LENGTH_SHORT).show();
    }

    public void onGoForward() {
        webView.goForward();
    }

    public void onCanGoBackOrForward() {
        int steps = Integer.parseInt(mEtStep.getText().toString());
        Toast.makeText(this, String.valueOf(webView.canGoBackOrForward(steps)), Toast.LENGTH_SHORT).show();

    }

    public void onGoBackOrForward() {
        int steps = Integer.parseInt(mEtStep.getText().toString());
        webView.goBackOrForward(steps);
    }

    public void onClearHistory() {
        webView.clearHistory();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_can_go_back:
                onCanGoBack();
                break;
            case R.id.btn_go_back:
                onGoBack();
                break;
            case R.id.btn_can_go_forward:
                onCanGoForward();
                break;
            case R.id.btn_go_forward:
                onGoForward();
                break;
            case R.id.btn_can_go_back_or_forward:
                onCanGoBackOrForward();
                break;
            case R.id.btn_go_back_or_forward:
                onGoBackOrForward();
                break;
            case R.id.btn_clear_history:
                onClearHistory();
                break;
        }
    }
}