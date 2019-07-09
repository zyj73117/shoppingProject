package com.wenzheng.pojo;

/**
 * 商品购物车
 * @author ZYJ
 *
 */
public class ShoppingCar {
    //商品编号
    private int goodsId;
    // 数量
    private int goodsNum;
    // 用户编号
    private int userId;

    public ShoppingCar(int goodsId, int goodsNum, int userId) {
        this.goodsId = goodsId;
        this.goodsNum = goodsNum;
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
