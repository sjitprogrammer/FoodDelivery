package com.aodev.fooddelivery.data;

public class CategoriesModel {

    private int image;
    private String text;

    public CategoriesModel(int image,String text){
        this.image = image;
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }
}
