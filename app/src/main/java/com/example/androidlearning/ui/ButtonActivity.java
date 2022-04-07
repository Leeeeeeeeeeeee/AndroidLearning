package com.example.androidlearning.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidlearning.R;

public class ButtonActivity extends AppCompatActivity {

    private Button mBtn6;
    private TextView mTv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button);
        //比较常用
        mBtn6 = findViewById(R.id.btn_6);
        mBtn6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ButtonActivity.this,"我又被点击了",Toast.LENGTH_LONG).show();
            }
        });
        mTv1 = findViewById(R.id.tv_1);
        mTv1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(ButtonActivity.this,"TextView被点击了",Toast.LENGTH_LONG).show();
            }
        });
    }

    //比较少用
    public void showToast(View view){
        Toast.makeText(this,"我被点击了",Toast.LENGTH_LONG).show();
    }
}