package com.android.baitap_firebase;

import java.util.Objects;

public class Fish {
    private String name, scienceName,characteristic, color;
    private int img;

    public Fish(String name, String scienceName, String characteristic, String color, int img) {
        this.name = name;
        this.scienceName = scienceName;
        this.characteristic = characteristic;
        this.color = color;
        this.img = img;
    }

    public Fish() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScienceName() {
        return scienceName;
    }

    public void setScienceName(String scienceName) {
        this.scienceName = scienceName;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
