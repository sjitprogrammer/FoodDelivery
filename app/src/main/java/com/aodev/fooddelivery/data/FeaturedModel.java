package com.aodev.fooddelivery.data;


import java.io.Serializable;

public class FeaturedModel implements Serializable {

    private int image;
    private double star;
    private String text;
    private String time;
    private double delivery;
    private double price;
    private String description;
    private String[] tags;
    private int amountIncart;

    public FeaturedModel(int image, double star, String text, String time, double delivery, double price, String description, String[] tags){
        this.image = image;
        this.text = text;
        this.star = star;
        this.time = time;
        this.delivery = delivery;
        this.price = price;
        this.description = description;
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

    public double getStar() {
        return star;
    }

    public double getDelivery() {
        return delivery;
    }

    public String getTime() {
        return time;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getAmountIncart() {
        return amountIncart;
    }

    public void setAmountIncart(int amountIncart) {
        this.amountIncart = amountIncart;
    }
}
