package com.example.androidlearning.activity.jump;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidlearning.R;

public class BActivity extends AppCompatActivity {

    private TextView mTvTitle;
    private Button mBtnJump1;
    private Button mBtnJump2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);

        Log.d("BActivity","---onCreate---");
        Log.d("BActivity","taskid:"+getTaskId()+"\t,hash:"+hashCode());
        logTaskName();

        mTvTitle = (TextView)findViewById(R.id.tv_title);
        mBtnJump1 = (Button)findViewById(R.id.btn_jump_1);
        mBtnJump2 = (Button)findViewById(R.id.btn_jump_2);

        Bundle bundle = getIntent().getExtras();
        String name = bundle.getString("name");
        int number = bundle.getInt("number");
        mTvTitle.setText(name+","+number);

        mBtnJump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("title","我回来了");
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        mBtnJump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BActivity.this,BActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("BActivity","---onCreate---");
        Log.d("BActivity","taskid:"+getTaskId()+"\t,hash:"+hashCode());
        logTaskName();
    }

    private void logTaskName(){
        ActivityInfo info = null;
        try {
            info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Log.d("BActivity",info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}