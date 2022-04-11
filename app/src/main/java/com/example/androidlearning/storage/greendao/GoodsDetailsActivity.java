package com.example.androidlearning.storage.greendao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.androidlearning.R;
import com.example.androidlearning.storage.greendao.model.GoodsModel;

public class GoodsDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private GoodsModel goodsModel;
    private GreenDaoManager greenDaoManager;
    private EditText mEtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_details);

        greenDaoManager = new GreenDaoManager(this);
        goodsModel = (GoodsModel) getIntent().getSerializableExtra("goodsModel");

        mEtInfo = findViewById(R.id.et_info);
        mEtInfo.setText(goodsModel.getInfo());
        findViewById(R.id.btn_delete).setOnClickListener(this);

        findViewById(R.id.btn_alter).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete: {
                greenDaoManager.deleteGoodsInfo(goodsModel);
                finish();
            }
            break;
            case R.id.btn_alter: {
                String info = mEtInfo.getText().toString();
                goodsModel.setInfo(info);
                greenDaoManager.alterGoodsInfo(goodsModel);
                finish();
            }
            break;
        }
    }
}