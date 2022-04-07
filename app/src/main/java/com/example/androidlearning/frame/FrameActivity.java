package com.example.androidlearning.frame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidlearning.R;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);

        findViewById(R.id.btn_okhttp).setOnClickListener(this);
        findViewById(R.id.btn_event_bus).setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_okhttp: {
                Intent intent = new Intent(FrameActivity.this, OKHttpActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_event_bus: {
                Intent intent = new Intent(FrameActivity.this, EventBusActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}