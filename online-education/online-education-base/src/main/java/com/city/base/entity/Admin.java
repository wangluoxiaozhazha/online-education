package com.city.base.entity;

public class Admin {
    Integer AdminID;
    String AdminName;
    String Password;

    public Integer getAdminID() {
        return AdminID;
    }

    public void setAdminID(Integer adminID) {
        AdminID = adminID;
    }

    public String getAdminName() {
        return AdminName;
    }

    public void setAdminName(String adminName) {
        AdminName = adminName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
