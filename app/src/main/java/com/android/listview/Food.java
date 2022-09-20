package com.android.listview;

public class Food {
    String name, description, price;
    int imgIndex;

    public Food(String name, String description, String price, int imgIndex) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgIndex = imgIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImgIndex() {
        return imgIndex;
    }

    public void setImgIndex(int imgIndex) {
        this.imgIndex = imgIndex;
    }
}
