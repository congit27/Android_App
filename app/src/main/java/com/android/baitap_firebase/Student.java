package com.android.baitap_firebase;

import java.util.Objects;

public class Student {
    private String code;
    private String name;

    public Student(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Student() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
