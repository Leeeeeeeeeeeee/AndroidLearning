package com.example.androidlearning.storage.greendao.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;

@Entity
public class GoodsModel implements Serializable {

    @Id(autoincrement = true)
    private Long id;//主键自增类型必须是Long，不能是long
    @Transient
    private static final long serialVersionUID = -8424037577440794897L;//序列化必须用到
    private Integer goodsId;

    private String name;

    private String icon;

    private String info;

    private String type;

    @Generated(hash = 1834473137)
    public GoodsModel(Long id, Integer goodsId, String name, String icon,
                      String info, String type) {
        this.id = id;
        this.goodsId = goodsId;
        this.name = name;
        this.icon = icon;
        this.info = info;
        this.type = type;
    }

    @Generated(hash = 971639536)
    public GoodsModel() {
    }

    @Override
    public String toString() {
        return "GoodsModel{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", info='" + info + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGoodsId() {
        return this.goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
