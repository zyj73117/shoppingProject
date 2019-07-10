package com.wenzheng.service;

import com.wenzheng.data.DataBase;
import com.wenzheng.pojo.*;
import com.wenzheng.util.DateUtil;
import com.wenzheng.util.GenericUtil;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * 购物系统，封装了系统应用中所有的业务逻辑
 *
 */
public class ShoppingSystem {
    //键盘扫描器
    private Scanner sc = new Scanner(System.in);
    //数据库
    private DataBase data = new DataBase();
    //用户ID
    private User loginUser;
    //自定义日期工具类
    private DateUtil dateUtil = new DateUtil();

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }

    /*
    * 启动系统
    * */
    public void start(){
        System.out.println("1.登录");
        System.out.println("2.注册");
        System.out.println("请选择：");
        int chooice = sc.nextInt();

        if (chooice == 1){
            login();
        }
        else if (chooice == 2){
            regist();
        }

    }

    /*登录*/
    public void login(){
        System.out.println("-----登录-----");
        System.out.println("请输入用户名： ");
        String userName = sc.next();
        System.out.println("请输入密码：  ");
        String passWord = sc.next();
        //判断输入的信息是否和数据库中记录的用户匹配

        //当前登录的用户
        User loginUser = validateUser(userName);
            //如果比较成功
            if (loginUser != null) {
                //判断用户密码是否正确
                if (passWord.equals(loginUser.getPassWord())) {//登录成功
                    this.loginUser = loginUser;
                    mainMenu();
                } else {
                    System.out.println("密码错误");
                    login();
                }

            }
            //如果比较失败，则提示错误信息
            //如果用户名不存在，则提示错误信息
            else {
                System.out.println("用户名不存在");
                login();
            }


        }

    /*注册*/
    public void regist(){
        System.out.println("-----注册-----");
        //输入注册的账号
        System.out.println("请输入要注册的用户名：");
        String userName = sc.next();
        //输入密码
        System.out.println("请输入注册账号密码：");
        String passWord = sc.next();

        //判断数据库中是否有相同的账号
        //遍历数据库中的用户名是否与注册的账号相同
        User user = validateUser(userName);

        if (user == null) {//如果用户不存在
            //将注册信息添加入User对象中
            User registUser = new User(++GenericUtil.genercUtil,userName,passWord);
            //将生成的对象添加入数据库中
            data.getUserData().add(registUser);
        }
        else {//如果用户存在
            System.out.println("用户名已存在");
            regist();
        }
        //提示注册成功
        System.out.println("注册成功");
        start();
    }

    //显示主菜单
    public void mainMenu(){
        System.out.println("-----主菜单-----");
        System.out.println("1.商品类别");
        System.out.println("2.我的购物车");
        System.out.println("3.我的订单");
        System.out.println("4.退出系统");
        System.out.println("请选择:");
        int chooice = sc.nextInt();
        switch (chooice){
            case 1:
                showGoodsClass();
                break;
            case 2:
                myShoppingCar();
                break;
            case 3:
                myOrders();
                break;
            case 4:
                start();
                break;
        }
    }

    /**
     * 验证用户名是否存在
     * @param userName
     * @return
     */
    public User validateUser(String userName){
        User loginUser = null;
        for(User user:data.getUserData()) {
            //判断输入的用户名和数据库中的用户名是否匹配
            if (user.getUserName().equals(userName)) {
                //如果匹配，则获取对应的用户对象
                loginUser = user;
                break;
            }
        }
        return loginUser;
    }


    /**
     * 查看商品类别
     */
    public void showGoodsClass(){
        System.out.println("-----商品类别-----");
        //创建Set集合来筛选不重复的商品类别名称
        HashSet<Integer> classSet = new HashSet<>();
        //遍历商品集合
        for (Goods goods : data.getGoodsData()){
            classSet.add(goods.getClassId());
        }
        //创建集合来存储商品类别
        ArrayList<GoodsClass> classList = new ArrayList<>();
        //遍历HashSet中的类别编号
        for (Integer classId:classSet) {
            //根据类别编号从数据库中查找相应的类别对象
            GoodsClass goodsClass = getGoodsClass(classId);
            //将类别对象添加至classList
            classList.add(goodsClass);
        }

        //对classList遍历，呈现类别名称
        for(int i = 0; i < classList.size(); i++) {

            GoodsClass goodsClass = classList.get(i);
            System.out.println(i+1+". "+goodsClass.getGoodsClassName());
        }
        System.out.println("请选择商品类别：（输入0返回上一层）");
        int chooice =sc.nextInt();
        //判断序号是否需要返回上一层
        if(chooice == 0){
            mainMenu();
        }else {
            //计算索引
            int index = chooice - 1;
            GoodsClass goodsClass = classList.get(index);
            showGoodsByClass(goodsClass);
        }




/*        ArrayList<Integer> classList = new ArrayList<>();
        //将HashSet中的类别编号写入classList中
        for (Integer classId:classSet){
            classList.add(classId);
        }
        //对classList遍历，呈现类别名称
        for (int i = 0;i < classList.size(); i++) {
            int classId = classList.get(i);
            String className = getClassName(classId);
            System.out.println((i + 1) + "." + className);
        }
        System.out.println("请选择：（输入0返回上一级）");
        int chooice = sc.nextInt();
        //根据选择项
        if (chooice == 0){
            mainMenu();
        }else {
            System.out.println("商品编号为：" + classList.get(chooice - 1));
        }*/

    }

    /**
     * 根据商品类别查看商品列表
     */
    public void showGoodsByClass(GoodsClass goodsClass){
        System.out.println("-----"+goodsClass.getGoodsClassName()+"-----");
        //创建goodsList集合，用来存储满足类型的商品
        ArrayList<Goods> goodsList = new ArrayList<>();
        //遍历数据库中的商品集合
        for (Goods goods : data.getGoodsData()) {
            //判断每个商品的类别编号是否和参数的类别编号相同
            if (goods.getClassId() == goodsClass.getGoodsClassId()){
            //如果相同，将商品添加至goodsList集合
                goodsList.add(goods);
            }
        }
        //遍历goodsList集合
        for (int i = 0;i < goodsList.size();i++){
            Goods goods = goodsList.get(i);
            //打印呈现序号以及商品名称
            System.out.println(i+1+"."+goods.getGoodsName());
        }
        System.out.println();
        int chooice = sc.nextInt();
        //判断选项
        if (chooice == 0){
            //返回商品类别
            showGoodsClass();
        }else {
            int index = chooice - 1 ;
            //根据索引找到选择的商品对象
            //显示商品详情
            showGoodsDetails(goodsList.get(index));
        }
    }



    /**
     *
     * 根据类别编号获取类别名称
     * @param classId
     * @return
     */
    public String getClassName(int classId){
        String className = null;
        //遍历数据库中的类别集合
        for (GoodsClass goodsClass : data.getGoodsClassData()){
            //判断类别编号是否相同
            if (classId == goodsClass.getGoodsClassId()){
                className = goodsClass.getGoodsClassName();
                break;
            }
        }
        return  className;
    }

    /**
     * 根据类别编号查询类别对象
     * @param classId
     * @return
     */
    public GoodsClass getGoodsClass(int classId){
        //创建集合存放数据
        GoodsClass goodsClass = null;
        for (GoodsClass goodsClassId : data.getGoodsClassData()){
            if (goodsClassId.getGoodsClassId() == classId){
                goodsClass = goodsClassId;
                break;
            }
        }
        return goodsClass;

    }

    /**
     * 查看商品详细信息
     *
     */
    public void showGoodsDetails(Goods goods){
        System.out.println("-----商品详情-----");
        System.out.println(goods.getGoodsName());
        System.out.println("品牌："+goods.getBrand());
        System.out.println("价格："+goods.getPrice());
        System.out.println("销量："+goods.getSellCount());
        System.out.println("库存："+goods.getStoage());
        System.out.println("是否要加入购物车：（y/n）");
        String chooice = sc.next();
        //判断选项
        if(chooice.equals("y")){
            System.out.println("请输入要添加的数量: ");
            int count = sc.nextInt();
            if (count >= 0 && count <= goods.getStoage()) {
                //添加购物车记录
                addShoppingCar(goods, count);
            }else{
                System.out.println("库存不足");
            }
            //返回商品类列表
            showGoodsClass();
        }else if(chooice.equals("n"))
        {
            showGoodsClass();
        }




    }

    /**
     * 将商品添加到购物车
     * @param goods
     * @param count
     */
    public void addShoppingCar(Goods goods,int count){
        //从数据库中根据登录人找到相应的购物车集合
        ArrayList<ShoppingCar> carList = data.getCarData().get(loginUser.getUserId());
        //如果是第一次查找需要创建一个新的购物车集合
        if (carList == null){
            ArrayList<ShoppingCar> list = new ArrayList<>();
            //将新的集合写回数据库
            data.getCarData().put(loginUser.getUserId(),list);
        }
        //从数据库中根据登录人找到对应的购物车集合
        carList = data.getCarData().get(loginUser.getUserId());
        //创建购物车记录
        ShoppingCar shoppingCar = new ShoppingCar(goods.getGoodsId(),count,loginUser.getUserId());

        //表示购物记录是否存在与购物集合中的标识索引，默认不存在
        int index = -1;
        //对用户的购物车集合进行遍历
        for (int i = 0; i<carList.size(); i++){
            ShoppingCar car = carList.get(i);
            //判断购物车集合是否已经存在该商品
            if (car.getGoodsId() == shoppingCar.getGoodsId()){
               //记录找到元素的索引
                index = i;
                break;
            }
            //如果不存在，则直接添加新的购物记录
        }
        if (index!=-1){
            //根据索引找到对应的购物记录
            ShoppingCar car = carList.get(index);
            //如果存在，则获取原有记录的商品数量
            int goodsCount = car.getGoodsNum();
            //将原有记录的商品数量累加现在新增的商品数量
            goodsCount = goodsCount + count;
            //将修改后的数量写入购物记录
            car.setGoodsNum(goodsCount);
            //将修改后的记录写回集合
            carList.set(index,car);
        }else {

            carList.add(shoppingCar);
        }
        //将集合写回数据库
        data.getCarData().put(loginUser.getUserId(),carList);

        System.out.println("已添加至购物车");

    }

    /**
     *
     * 根据商品编号获得商品对象
     * @param goodsId
     * @return
     */
    public Goods getGoods(int goodsId){
        Goods goods = null;
        //遍历商品集合
        for (Goods g:data.getGoodsData()){
            //判断商品编号是否和集合中商品的编号相同
            if (g.getGoodsId() == goodsId){
                //记录下当前商品
                goods = g;
                //退出循环
                break;
            }
        }
        return goods;
    }
    /**
     * 我的购物车
     */
    public void myShoppingCar(){
        System.out.println("-----购物车-----");
        //查找登录人的购物车数据
        ArrayList<ShoppingCar> list = data.getCarData().get(loginUser.getUserId());

        if (list == null || list.size() == 0){
            System.out.println("购物车内没有商品");
            mainMenu();
        }else {
            //合计总价
            double total = 0;
            //遍历购物车集合
            for (ShoppingCar car: list){
                //根据购物车中的商品编号获得商品对象
                Goods goods = getGoods(car.getGoodsId());
                //计算商品的总价
                double price = car.getGoodsNum()*goods.getPrice();
                //累计合计总价
                total += price;
                System.out.println("商品名称："+goods.getGoodsName()+"商品编号"+car.getGoodsId()+"商品价格：￥"+goods.getPrice()+"\t数量"+car.getGoodsNum());
            }
            System.out.println("合计：￥"+total);
            System.out.println("是否要结算：（y/n）");
            String chooice = sc.next();
            if (chooice.equals("y")){
                //生成订单
                pay();
            }
        }
        //返回主菜单
        mainMenu();
    }

    /**
     * 支付结算
     *
     */
    public void pay(){
        //获取登录人的购物记录
        ArrayList<Order> orderList = data.getOrderData().get(loginUser.getUserId());
        //判断是否订单记录
        if (orderList == null){
            //创建一个新的订单记录
            orderList = new ArrayList<>();
            //写入数据库
            data.getOrderData().put(loginUser.getUserId(),orderList);
        }
        //重新获取登录人的订单记录
        orderList = data.getOrderData().get(loginUser.getUserId());

        //获取登录人的购物记录
        ArrayList<ShoppingCar> list = data.getCarData().get(loginUser.getUserId());
        //遍历登录人的购物记录
        for (ShoppingCar car : list ){
            //根据商品编号获得商品
            Goods goods = getGoods(car.getGoodsId());
            //根据商品的类别编号获得类别对象
            GoodsClass goodsClass = getGoodsClass(goods.getClassId());
            //生成系统时间
            Date now = new Date();
            //生成订单编号   商品类型简写+商品编号+交易人编号+交易时间毫秒值
            String orderId = goodsClass.getSimplyName()+goods.getGoodsId()+loginUser.getUserId()+now.getTime();
            System.out.println("订单编号："+orderId);
            //根据购物车中的购物记录，生成订单对象
            Order order = new Order(orderId,loginUser.getUserId(),car.getGoodsId(),car.getGoodsNum(),now);
            //将订单写入订单集合
            orderList.add(order);

        }

        //将生成的订单写入数据库
        data.getOrderData().put(loginUser.getUserId(),orderList);
        //清空购物车
        data.getCarData().get(loginUser.getUserId()).clear();
        try {
            //以文件保存的方式打印购物小票
            FileWriter writer = new FileWriter(loginUser.getUserName()+"购物小票.txt");
            //记录合计总价
            double totalPrice = 0;
            //使用FileWrite将订单数据写入文件
            for (Order order: orderList){
                writer.write("交易时间："+dateUtil.parseDateToString(order.getTradeTime())+"\r\n");
                //获取商品
                Goods goods = getGoods(order.getGoodsId());
                writer.write("商品名称："+goods.getGoodsName()+"\r\n");
                writer.write("价格：￥"+goods.getPrice()+"\r\n");
                writer.write("数量："+order.getGoodsSum()+"\r\n");
                writer.write("总价：￥"+order.getGoodsSum()*goods.getPrice()+"\r\n");
                totalPrice += order.getGoodsSum()*goods.getPrice();

                //减少库存的数量
                
            }
            writer.write("合计：￥"+totalPrice);
            //关闭writer数据流
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查看订单
     */
    public void myOrders(){
        System.out.println("-----交易订单-----");
        //获取登录人的订单记录
        ArrayList<Order> orderList = data.getOrderData().get(loginUser.getUserId());
        //打印显示登录人的订单数据
        for (Order order : orderList){
            System.out.println("交易时间"+dateUtil.parseDateToString(order.getTradeTime()));
            //根据商品编号查询商品对象
            Goods goods = getGoods(order.getGoodsId());
            System.out.println("商品名称："+goods.getGoodsName()+"\t价格：￥"+goods.getPrice()+"\t数量"+order.getGoodsSum()+"\t总价:￥"+order.getGoodsSum()*goods.getPrice());
        }

        //返回主菜单
        mainMenu();
    }

}
