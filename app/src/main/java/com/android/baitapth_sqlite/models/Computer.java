package com.android.baitapth_sqlite.models;

public class Computer {
    private String name;
    private String price;
    private String categoryId;
    private byte[] img;

    public Computer(String name, String price, String categoryId, byte[] img) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }
}
