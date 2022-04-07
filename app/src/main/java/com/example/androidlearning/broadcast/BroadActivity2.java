package com.example.androidlearning.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlearning.R;

public class BroadActivity2 extends AppCompatActivity {

    private Button mBtnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad2);

        mBtnClick = findViewById(R.id.btn_click);
        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.skypan.update");
//                LocalBroadcastManager.getInstance(BroadActivity2.this).sendBroadcast(intent);
            }
        });
    }
}