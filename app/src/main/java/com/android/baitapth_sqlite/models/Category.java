package com.android.baitapth_sqlite.models;

public class Category {
    private String name;
    private byte[] img;
    private String id;

    public Category(String name, byte[] img, String id) {
        this.name = name;
        this.img = img;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
