package com.example.androidlearning.activity.jump;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.androidlearning.R;

public class AActivity extends AppCompatActivity {

    private Button mBtnJump1;
    private Button mBtnJump2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Log.d("AActivity","---onCreate---");
        Log.d("AActivity","taskid:"+getTaskId()+"\t,hash:"+hashCode());
        logTaskName();

        mBtnJump1 = (Button)findViewById(R.id.btn_jump_1);
        mBtnJump2 = (Button)findViewById(R.id.btn_jump_2);

        mBtnJump1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显式1
                Intent intent = new Intent(AActivity.this,BActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name","天哥");
                bundle.putInt("number",101);
                intent.putExtras(bundle);
                startActivity(intent);
//                startActivityForResult(intent,0);

                //显式2
//                Intent intent = new Intent();
//                intent.setClass(AActivity.this,BActivity.class);
//                startActivity(intent);

                //显式3
//                Intent intent = new Intent();
//                intent.setClassName(AActivity.this,"com.example.hello.jump.BActivity");
//                startActivity(intent);

                //显式4
//                Intent intent = new Intent();
//                intent.setComponent(new ComponentName(AActivity.this,"com.example.hello.jump.BActivity"));
//                startActivity(intent);

                //隐式
//                Intent intent = new Intent();
//                intent.setAction("com.example.hello.test.BActivity");
//                startActivity(intent);
            }
        });
        mBtnJump2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AActivity.this,AActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(AActivity.this,data.getExtras().getString("title"),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("AActivity","---onCreate---");
        Log.d("AActivity","taskid:"+getTaskId()+"\t,hash:"+hashCode());
        logTaskName();
    }

    private void logTaskName(){
        ActivityInfo info = null;
        try {
            info = getPackageManager().getActivityInfo(getComponentName(), PackageManager.GET_META_DATA);
            Log.d("AActivity",info.taskAffinity);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}