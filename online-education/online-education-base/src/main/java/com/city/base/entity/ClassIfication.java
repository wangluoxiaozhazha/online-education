package com.city.base.entity;

import java.text.NumberFormat;

/*
 *分类
 */
public class ClassIfication {

    private Integer ClassID;//分类id
    private String ClassName;//分类名
    private Integer Number;//个数
    private String percentage;//占总资源的百分比

    public void calculatePercentage(Integer sum){

        NumberFormat numberFormat = NumberFormat.getInstance();

        // 设置精确到小数点后2位

        numberFormat.setMaximumFractionDigits(2);

        percentage = numberFormat.format((float) Number / (float) sum * 100);


    }
    public Integer getClassID() {
        return ClassID;
    }

    public void setClassID(Integer classID) {
        ClassID = classID;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public Integer getNumber() {
        return Number;
    }

    public void setNumber(Integer number) {
        Number = number;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
