package com.wenzheng.data;

import com.wenzheng.pojo.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 数据库类，保存了系统中所有存储的数据内容
 *
 */
public class DataBase {
    //用户数据
    private ArrayList<User> userData = new ArrayList<>();
    //商品数据
    private ArrayList<Goods> goodsData = new ArrayList<>();
    //商品类别
    private ArrayList<GoodsClass> goodsClassData = new ArrayList<>();
    //订单数据
    private HashMap<Integer,ArrayList<Order>> orderData = new HashMap<>();
    //购物车数据
    private HashMap<Integer,ArrayList<ShoppingCar>> carData = new HashMap<>();

    public ArrayList<User> getUserData() {
        return userData;
    }

    public void setUserData(ArrayList<User> userData) {
        this.userData = userData;
    }

    public ArrayList<Goods> getGoodsData() {
        return goodsData;
    }

    public void setGoodsData(ArrayList<Goods> goodsData) {
        this.goodsData = goodsData;
    }

    public ArrayList<GoodsClass> getGoodsClassData() {
        return goodsClassData;
    }

    public void setGoodsClassData(ArrayList<GoodsClass> goodsClassData) {
        this.goodsClassData = goodsClassData;
    }

    public HashMap<Integer, ArrayList<Order>> getOrderData() {
        return orderData;
    }

    public void setOrderData(HashMap<Integer, ArrayList<Order>> orderData) {
        this.orderData = orderData;
    }

    public HashMap<Integer, ArrayList<ShoppingCar>> getCarData() {
        return carData;
    }

    public void setCarData(HashMap<Integer, ArrayList<ShoppingCar>> carData) {
        this.carData = carData;
    }

    /**
    * 构造方法，在类创建时对数据进行初始化
    *
     */
    public DataBase(){
        //初始化用户数据
        userData.add(new User(1,"tom","123456"));
        userData.add(new User(2,"keykey","123456"));
        //初始化商品类别
        goodsClassData.add(new GoodsClass(1001,"家用电器","jydq"));
        goodsClassData.add(new GoodsClass(1002,"电脑","dn"));
        goodsClassData.add(new GoodsClass(1003,"手机","sj"));
        goodsClassData.add(new GoodsClass(1004,"玩具","wj"));
        //初始化商品数据
        goodsData.add(new Goods(100,"SONY电视",29999,1001,10,"1.jpg","SONY",5));
        goodsData.add(new Goods(101,"Dell G5",6399,1002,10,"1.jpg","SONY",5));
        goodsData.add(new Goods(102,"Iphone XR",5499,1003,10,"1.jpg","SONY",5));
        goodsData.add(new Goods(103,"联想电脑",5699,1002,10,"1.jpg","SONY",5));
        goodsData.add(new Goods(104,"Dell G3",5299,1002,10,"1.jpg","SONY",5));
        goodsData.add(new Goods(105,"海信电视",4699,1001,10,"1.jpg","SONY",5));
    }
}
