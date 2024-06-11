package com.example.bigproject;

public class Role {
    private String name;
    private int imageId;
    private String videoUrl;  //在原先的基础上新增Url 来播放视频链接

    public Role(String name , int imageId , String videoUrl){
        this.name = name ;
        this.imageId = imageId;
        this.videoUrl = videoUrl;
    }

    public String getName(){return name;}

    public int getImageId(){return imageId;}

    public String  getVideoUrl(){return videoUrl;}
}
