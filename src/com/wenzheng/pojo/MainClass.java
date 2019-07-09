package com.wenzheng.pojo;

import java.util.ArrayList;

/**
 * 三个集合：用户数据、商品数据、商品类别
 * @author ZYJ
 */
public class MainClass {

    public static void main(String args[]){
        //创建三个集合：用户数据、商品数据、商品类别
        ArrayList<User> userList = new ArrayList<>();

        ArrayList<Goods> goodsList = new ArrayList<>();

        ArrayList<GoodsClass> goodsClassList = new ArrayList<>();

        //创建用户对象
        User user1 = new User(1,"tony","1234");
        User user2 = new User(2,"tom","1234");
        User user3 = new User(3,"lion","1234");

        //添加用户对象
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);

        //创建商品对象
        Goods goods1 = new Goods(1,"拯救者Y7000P",9099.00,1,100,"image\1.jpg","联想",0);
        Goods goods2 = new Goods(2,"MateBook 13",5699.00,2,100,"image\2.jpg","华为",0);
        Goods goods3 = new Goods(3,"Surface Book 2 ",12388.00,3,100,"image\3.jpg","微软",0);

        //添加商品对象
        goodsList.add(goods1);
        goodsList.add(goods2);
        goodsList.add(goods3);

        //创建商品类别对象
        GoodsClass goodsClass1 = new GoodsClass(1,"游戏本","yxb");
        GoodsClass goodsClass2 = new GoodsClass(2,"轻薄本","qbb");
        GoodsClass goodsClass3 = new GoodsClass(3,"二合一笔记本","ehy");

        //添加商品类别对象
        goodsClassList.add(goodsClass1);
        goodsClassList.add(goodsClass2);
        goodsClassList.add(goodsClass3);

    }
}
