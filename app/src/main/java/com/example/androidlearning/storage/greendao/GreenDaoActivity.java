package com.example.androidlearning.storage.greendao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.androidlearning.R;
import com.example.androidlearning.storage.greendao.model.GoodsModel;

import java.util.List;

public class GreenDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private GreenDaoManager greenDaoManager;
    private GoodsAdapter goodsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_green_dao);

        initDB();

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        List<GoodsModel> goodsModels = greenDaoManager.queryGoods();
        goodsAdapter.setGoodsModels(goodsModels);
    }

    private void initView() {
        findViewById(R.id.tv_insert).setOnClickListener(this);
        findViewById(R.id.tv_all).setOnClickListener(this);
        findViewById(R.id.tv_fruits).setOnClickListener(this);
        findViewById(R.id.tv_snacks).setOnClickListener(this);
        RecyclerView mRvShow = findViewById(R.id.rv_show);
        goodsAdapter = new GoodsAdapter(this);
        mRvShow.setLayoutManager(new LinearLayoutManager(this));
        mRvShow.setAdapter(goodsAdapter);
    }

    private void initDB() {
        greenDaoManager = new GreenDaoManager(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_insert: {
                greenDaoManager.insertGoods();
            }
            break;
            case R.id.tv_all: {
                List<GoodsModel> goodsModels = greenDaoManager.queryGoods();
                goodsAdapter.setGoodsModels(goodsModels);
            }
            break;
            case R.id.tv_fruits: {
                List<GoodsModel> goodsModels = greenDaoManager.queryFruits();
                goodsAdapter.setGoodsModels(goodsModels);
            }
            break;
            case R.id.tv_snacks: {
                List<GoodsModel> goodsModels = greenDaoManager.querySnacks();
                goodsAdapter.setGoodsModels(goodsModels);
            }
            break;
        }
    }
}