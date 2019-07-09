package com.wenzheng.pojo;

/**
 * 商品类别
 * @author ZYJ
 */
public class GoodsClass {
    //类型编号
    private int goodsClassId;
    //类型名称
    private String goodsClassName;
    //类型简称
    private String simplyName;

    public GoodsClass(int goodsClassId, String goodsClassName, String simplyName) {
        this.goodsClassId = goodsClassId;
        this.goodsClassName = goodsClassName;
        this.simplyName = simplyName;
    }

    public String getSimplyName() {
        return simplyName;
    }

    public void setSimplyName(String simplyName) {
        this.simplyName = simplyName;
    }

    public int getGoodsClassId() {
        return goodsClassId;
    }

    public void setGoodsClassId(int goodsClassId) {
        this.goodsClassId = goodsClassId;
    }

    public String getGoodsClassName() {
        return goodsClassName;
    }

    public void setGoodsClassName(String goodsClassName) {
        this.goodsClassName = goodsClassName;
    }
}
