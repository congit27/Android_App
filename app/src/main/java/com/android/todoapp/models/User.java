package com.android.todoapp.models;

public class User {
    private String id, username, password, name, company;

    public User(String id, String username, String password, String name, String company) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.company = company;
    }

    public User(String username, String password, String name, String company) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
