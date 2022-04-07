package com.example.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.androidlearning.R;

public class ImageViewActivity extends AppCompatActivity {

    private ImageView mTv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        mTv2 = findViewById(R.id.iv_2);
        Glide.with(this).load("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png").into(mTv2);
    }
}