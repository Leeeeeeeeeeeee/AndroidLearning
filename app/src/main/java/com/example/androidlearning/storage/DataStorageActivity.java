package com.example.androidlearning.storage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidlearning.R;
import com.example.androidlearning.storage.greendao.GreenDaoActivity;

public class DataStorageActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnSharedPreferences;
    private Button mBtnFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_storage);

        mBtnSharedPreferences = (Button) findViewById(R.id.btn_sharedpreferences);
        mBtnFile = (Button) findViewById(R.id.btn_file);

        mBtnSharedPreferences.setOnClickListener(this);
        mBtnFile.setOnClickListener(this);

        findViewById(R.id.btn_db).setOnClickListener(this);
        findViewById(R.id.btn_greendao).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_sharedpreferences:
                intent = new Intent(DataStorageActivity.this, SharedPerferencesActivity.class);
                break;
            case R.id.btn_file:
                intent = new Intent(DataStorageActivity.this, FileActivity.class);
                break;
            case R.id.btn_db:
                intent = new Intent(DataStorageActivity.this, DatabaseActivity.class);
                break;
            case R.id.btn_greendao:
                intent = new Intent(DataStorageActivity.this, GreenDaoActivity.class);
                break;
        }
        startActivity(intent);
    }
}