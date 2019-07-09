package com.wenzheng.pojo;

/**
 * @author ZYJ
 * @version 1.0
 */
public class User {
    //用户编号
    private int userId;
    //用户姓名
    private String userName;
    //用户密码
    private  String passWord;

    public User(int userId, String userName, String passWord) {
        this.userId = userId;
        this.userName = userName;
        this.passWord = passWord;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
