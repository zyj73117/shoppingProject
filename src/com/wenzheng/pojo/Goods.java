package com.wenzheng.pojo;
import java.util.Date;

/**
 * 商品类
 * @author ZYJ
 * @version 1.0
 */
public class Goods {
    //商品编号
    private int goodsId;
    //商品名称
    private String goodsName;
    //价格
    private double price;
    //类型编号
    private int classId;
    //库存
    private int stoage;
    //图片路径
    private String goodsImage;
    //品牌
    private String brand;
    //商品销量
    private int sellCount;
    //支付时间
    Date payTime;

    public Goods(int goodsId, String goodsName, double price, int classId, int stoage, String goodsImage, String brand, int sellCount) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.price = price;
        this.classId = classId;
        this.stoage = stoage;
        this.goodsImage = goodsImage;
        this.brand = brand;
        this.sellCount = sellCount;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsClassName) {
        this.goodsName = goodsClassName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getStoage() {
        return stoage;
    }

    public void setStoage(int stoage) {
        this.stoage = stoage;
    }

    public String getGoodsImage() {
        return goodsImage;
    }

    public void setGoodsImage(String goodsImage) {
        this.goodsImage = goodsImage;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSellCount() {
        return sellCount;
    }

    public void setSellCount(int sellCount) {
        this.sellCount = sellCount;
    }
}
