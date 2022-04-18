package com.example.androidlearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlearning.asynctask.AsyncTaskActivity;
import com.example.androidlearning.broadcast.BroadActivity;
import com.example.androidlearning.event.EventActivity;
import com.example.androidlearning.frame.FrameActivity;
import com.example.androidlearning.webview.BaseWebViewActivity;
import com.example.androidlearning.storage.DataStorageActivity;
import com.example.androidlearning.fragment.ContainerActivity;
import com.example.androidlearning.handler.HandlerActivity;
import com.example.androidlearning.layout.LayoutActivity;
import com.example.androidlearning.network.NetworkOperationActivity;
import com.example.androidlearning.ui.UIActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        Button mBtnLayout = findViewById(R.id.btn_layout);
        Button mBtnUI = findViewById(R.id.btn_ui);
        Button mBtnFragment = findViewById(R.id.btn_fragment);
        Button mBtnEvent = findViewById(R.id.btn_event);
        Button mBtnDataStorage = findViewById(R.id.btn_local_storage);
        Button mBtnBroad = findViewById(R.id.btn_broad);
        Button mBtnObjectAnim = findViewById(R.id.btn_objectanim);
        Button mBtnNetworkOperation = findViewById(R.id.btn_network_operation);
        Button mBtnHandler = findViewById(R.id.btn_handler);
        Button mBtnAsyncTask = findViewById(R.id.btn_async_task);

        OnClick onClick = new OnClick();

        mBtnLayout.setOnClickListener(onClick);
        mBtnUI.setOnClickListener(onClick);
        mBtnFragment.setOnClickListener(onClick);
        mBtnEvent.setOnClickListener(onClick);
        mBtnDataStorage.setOnClickListener(onClick);
        mBtnBroad.setOnClickListener(onClick);
        mBtnObjectAnim.setOnClickListener(onClick);
        mBtnNetworkOperation.setOnClickListener(onClick);
        mBtnHandler.setOnClickListener(onClick);
        mBtnAsyncTask.setOnClickListener(onClick);

        findViewById(R.id.btn_frame).setOnClickListener(onClick);
        findViewById(R.id.btn_activity).setOnClickListener(onClick);
        findViewById(R.id.btn_web_view).setOnClickListener(onClick);
    }

    class OnClick implements View.OnClickListener {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void onClick(View v) {
            Intent intent = null;
            switch (v.getId()) {
                case R.id.btn_layout:
                    intent = new Intent(MainActivity.this, LayoutActivity.class);
                    break;
                case R.id.btn_ui:
                    intent = new Intent(MainActivity.this, UIActivity.class);
                    break;
                case R.id.btn_activity:
                    break;
                case R.id.btn_fragment:
                    intent = new Intent(MainActivity.this, ContainerActivity.class);
                    break;
                case R.id.btn_event:
                    intent = new Intent(MainActivity.this, EventActivity.class);
                    break;
                case R.id.btn_local_storage:
                    intent = new Intent(MainActivity.this, DataStorageActivity.class);
                    break;
                case R.id.btn_broad:
                    intent = new Intent(MainActivity.this, BroadActivity.class);
                    break;
                case R.id.btn_objectanim:
                    intent = new Intent(MainActivity.this, ObjectAnimationActivity.class);
                    break;
                case R.id.btn_network_operation:
                    intent = new Intent(MainActivity.this, NetworkOperationActivity.class);
                    break;
                case R.id.btn_handler:
                    intent = new Intent(MainActivity.this, HandlerActivity.class);
                    break;
                case R.id.btn_async_task:
                    intent = new Intent(MainActivity.this, AsyncTaskActivity.class);
                    break;
                case R.id.btn_frame:
                    intent = new Intent(MainActivity.this, FrameActivity.class);
                    break;
                case R.id.btn_web_view:
                    intent = new Intent(MainActivity.this, BaseWebViewActivity.class);
                    break;
            }
            startActivity(intent);
        }

    }
}