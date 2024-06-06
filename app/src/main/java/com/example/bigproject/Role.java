package com.example.bigproject;

public class Role {
    private String name;
    private int imageId;
    private String videoUrl;

    public Role(String name , int imageId , String videoUrl){
        this.name = name ;
        this.imageId = imageId;
        this.videoUrl = videoUrl;
    }

    public String getName(){return name;}

    public int getImageId(){return imageId;}

    public String  getVideoUrl(){return videoUrl;}
}