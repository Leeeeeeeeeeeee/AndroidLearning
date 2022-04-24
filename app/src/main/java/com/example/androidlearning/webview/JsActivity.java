package com.example.androidlearning.webview;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebView;

import com.example.androidlearning.R;

public class JsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);

        WebView webView = findViewById(R.id.web_view);

        findViewById(R.id.btn_alert).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

//                webView.loadUrl("javascript:showAlert()");
//                webView.loadUrl("javascript:alert(sum(2,3)");
                webView.evaluateJavascript("javascript:alert(sum(2,3)", new ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {

                    }
                });
            }
        });
    }
}