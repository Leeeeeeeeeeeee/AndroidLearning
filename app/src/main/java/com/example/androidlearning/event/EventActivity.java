package com.example.androidlearning.event;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.androidlearning.R;
import com.example.androidlearning.util.ToastUtil;

public class EventActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnEvent;
    private Button mBtnMyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        mBtnEvent = (Button)findViewById(R.id.btn_event);
        mBtnMyButton = (Button)findViewById(R.id.btn_mybutton);

        //内部类实现
        mBtnEvent.setOnClickListener(new OnClick());
        //匿名内部类
        mBtnEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("匿名内部类","click");
                ToastUtil.showMsg(EventActivity.this,"click...");
            }
        });
        //通过事件源所在的类实现
        mBtnEvent.setOnClickListener(EventActivity.this);
        //通过外部类实现
        mBtnEvent.setOnClickListener(new MyClickListener(EventActivity.this));

        //监听优先于回调
        mBtnMyButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Listener","---onTouch---");
                        break;
                }
                return false;
            }
        });
        //先触摸后点击
        mBtnMyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Listener","---onClick---");
            }
        });
        mBtnMyButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_event:
                Log.d("事件源所在的类","click");
                ToastUtil.showMsg(EventActivity.this,"click...");
                break;
        }
    }

    class OnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_event:
                    Log.d("内部类","click");
                    ToastUtil.showMsg(EventActivity.this,"click...");
                    break;
            }
        }
    }

    public void show(View v){
        switch (v.getId()){
            case R.id.btn_event:
                Log.d("布局","click");
                //只会使用最后一个监听器，布局是最先绑定的
                ToastUtil.showMsg(EventActivity.this,"click...");
                break;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                Log.d("Activity","---onTouchEvent---");
                break;
        }
        return true;
    }
}