package com.example.androidlearning.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidlearning.R;
import com.example.androidlearning.layout.gridview.GridViewActivity;
import com.example.androidlearning.layout.recyclerview.RecyclerViewActivity;

public class LayoutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout);

        findViewById(R.id.btn_gridview).setOnClickListener(this);
        findViewById(R.id.btn_recyclerview).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gridview: {
                Intent intent = new Intent(LayoutActivity.this, GridViewActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.btn_recyclerview: {
                Intent intent = new Intent(LayoutActivity.this, RecyclerViewActivity.class);
                startActivity(intent);
            }
            break;
        }
    }
}