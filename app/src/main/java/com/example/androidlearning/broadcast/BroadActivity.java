package com.example.androidlearning.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidlearning.R;

public class BroadActivity extends AppCompatActivity {

    private Button mBtnClick;
    private TextView mTvTest;
    private MyBroadcast mBroadcast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broad);

        mBtnClick = (Button)findViewById(R.id.btn_click);
        mTvTest = (TextView)findViewById(R.id.tv_test);

        mBtnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BroadActivity.this,BroadActivity2.class);
                startActivity(intent);
            }
        });

        mBroadcast = new MyBroadcast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.skypan.update");
//        LocalBroadcastManager.getInstance(this).registerReceiver(mBroadcast.intentFilter);
    }


    private class MyBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "com.skypan.update":
                    mTvTest.setText("123");
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        LocalBroadcastManager.getInstance(this).unregisterReceiver(mBroadcast);
    }
}