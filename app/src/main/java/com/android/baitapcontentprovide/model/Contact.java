package com.android.baitapcontentprovide.model;

import java.io.Serializable;

public class Contact implements Serializable {
    private String phone;
    private String name;
    private int img;

    public Contact(String phone, String name, int img) {
        this.phone = phone;
        this.name = name;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tên: "+name+"\nPhone: "+ this.phone;
    }
}
