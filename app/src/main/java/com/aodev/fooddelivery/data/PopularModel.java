package com.aodev.fooddelivery.data;

public class PopularModel {
    private int image;
    private float rate;
    private String text;

    public PopularModel(int image,float rate,String text){
        this.image = image;
        this.text = text;
        this.rate = rate;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public float getRate() {
        return rate;
    }
}
