package com.jazzi.onlinestudy.entity;

public class School {
    private String name;
    private int imageId;

    public School(String name, int  imageId){
        this.name=name;
        this.imageId=imageId;
    }

    public String getName(){
        return name;
    }
    public int getImageId(){
        return imageId;
    }

}
