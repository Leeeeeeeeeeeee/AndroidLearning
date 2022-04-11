package com.example.androidlearning.storage.greendao;

import android.content.Context;

import com.example.androidlearning.storage.greendao.model.GoodsModel;
import com.example.androidlearning.storage.greendao.model.GoodsModelDao;
import com.example.androidlearning.storage.greendao.utils.DataUtils;

import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class GreenDaoManager {

    private final Context context;
    private final GoodsModelDao goodsModelDao;

    public GreenDaoManager(Context context) {
        this.context = context;
        goodsModelDao = GreenDaoApplication.daoSession.getGoodsModelDao();
    }

    public void insertGoods() {
        String json = DataUtils.getJson("goods.json", context);
        List<GoodsModel> goodsModels = DataUtils.getGoodsModels(json);
        goodsModelDao.insertInTx(goodsModels);
    }

    public List<GoodsModel> queryGoods() {
        QueryBuilder<GoodsModel> goodsModelQueryBuilder = goodsModelDao.queryBuilder().orderAsc(GoodsModelDao.Properties.GoodsId);
        return goodsModelQueryBuilder.list();
    }

    public List<GoodsModel> queryFruits() {
        QueryBuilder<GoodsModel> goodsModelQueryBuilder = goodsModelDao.queryBuilder().where(GoodsModelDao.Properties.Type.eq("0")).orderAsc(GoodsModelDao.Properties.GoodsId);
        return goodsModelQueryBuilder.list();
    }

    public List<GoodsModel> querySnacks() {
        QueryBuilder<GoodsModel> goodsModelQueryBuilder = goodsModelDao.queryBuilder().where(GoodsModelDao.Properties.Type.eq("1")).orderAsc(GoodsModelDao.Properties.GoodsId);
        return goodsModelQueryBuilder.list();
    }

    public void deleteGoodsInfo(GoodsModel goodsModel) {
        goodsModelDao.delete(goodsModel);
    }

    public void alterGoodsInfo(GoodsModel goodsModel) {
        goodsModelDao.update(goodsModel);
    }
}
