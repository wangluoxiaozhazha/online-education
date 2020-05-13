package com.city.base.entity;

/*
 *用户类
 */
public class User {


    private Integer UserID;//用户id
    private String UserName;//用户名称
    private String Telephone;//电话号码
    private String Password;//密码
    private String UimagePath;//头像路径

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUimagePath() {
        return UimagePath;
    }

    public void setUimagePath(String uimagePath) {
        UimagePath = uimagePath;
    }


}
