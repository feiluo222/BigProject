package com.example.bigproject;

public class Game {
    private String name;
    private int imageId;

    public Game(String name , int imageId){
        this.name = name ;
        this.imageId = imageId;
    }

    public String getName(){
        return name;
    }

    public int getImageId(){
        return imageId;
    }
}