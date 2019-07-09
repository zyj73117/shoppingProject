package com.wenzheng.pojo;

import java.util.Date;

/**
 * 商品订单
 * @author ZYJ
 *
 */
public class Order {
    //订单编号  使用商品类型的简写+商品编号+交易人编号+交易时间毫秒值
    private String orderId;
    // 用户编号
    private int userId;
    // 商品编号
    private int goodsId;
    // 销售数量
    private int goodsSum;
    // 交易时间
    private Date tradeTime;

    public Order(String orderId, int userId, int goodsId, int goodsSum, Date tradeTime) {
        this.orderId = orderId;
        this.userId = userId;
        this.goodsId = goodsId;
        this.goodsSum = goodsSum;
        this.tradeTime = tradeTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getGoodsSum() {
        return goodsSum;
    }

    public void setGoodsSum(int goodsSum) {
        this.goodsSum = goodsSum;
    }
}
