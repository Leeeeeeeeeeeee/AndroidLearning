package com.example.androidlearning.fragment;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.androidlearning.R;

public class ContainerActivity extends AppCompatActivity implements AFragment.IOnMessageClick{

    private AFragment aFragment;
//    private BFragment bFragment;
//    private Button mBtnChange;
    private TextView mTvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        mTvTitle = (TextView)findViewById(R.id.tv_title);
//        mBtnChange = (Button)findViewById(R.id.btn_change);
//        mBtnChange.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(bFragment == null){
//                    bFragment = new BFragment();
//                }
//                getSupportFragmentManager().beginTransaction().replace(R.id.fl_container,bFragment).commit();
//            }
//        });

        //实例化
        aFragment = AFragment.newInstance("我是参数");
        //把AFragment添加到Activity中
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container,aFragment).commitAllowingStateLoss();
    }

    public void setData(String text){
        mTvTitle.setText(text);
    }

    @Override
    public void onClick(String text){
        mTvTitle.setText(text);
    }

}