package com.example.androidlearning.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.androidlearning.R;

public class SharedPerferencesActivity extends AppCompatActivity {

    private EditText mEtName;
    private Button mBtnSave,mBtnShow;
    private TextView mTvContent;
    private SharedPreferences mSharedPerferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_perferences);

        mEtName = (EditText)findViewById(R.id.et_name);
        mBtnSave = (Button)findViewById(R.id.btn_save);
        mBtnShow = (Button)findViewById(R.id.btn_show);
        mTvContent = (TextView)findViewById(R.id.tv_content);

        mSharedPerferences = getSharedPreferences("data",MODE_PRIVATE);
        mEditor = mSharedPerferences.edit();

        mBtnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.putString("name",mEtName.getText().toString());
                mEditor.commit();
            }
        });
        mBtnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvContent.setText(mSharedPerferences.getString("name",""));
            }
        });
    }
}