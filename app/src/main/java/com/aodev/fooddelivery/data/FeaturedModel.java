package com.aodev.fooddelivery.data;

import java.util.Arrays;

public class FeaturedModel {

    private int image;
    private float star;
    private String text;
    private String time;
    private String delivery;
    private String[] tags;

    public FeaturedModel(int image,float star,String text,String time,String delivery,String[] tags){
        this.image = image;
        this.text = text;
        this.star = star;
        this.time = time;
        this.delivery = delivery;
        this.tags = tags;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }

    public String[] getTags() {
        return tags;
    }

    public float getStar() {
        return star;
    }

    public String getDelivery() {
        return delivery;
    }

    public String getTime() {
        return time;
    }
}
