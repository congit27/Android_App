package com.android.todoapp.models;

import java.io.Serializable;

public class Task implements Serializable {
    private String id;
    private String userId;
    private String title;
    private String startTime;
    private String endTime;
    private String date;
    private Boolean state;

    public Task() {
    }

    public Task(String userId, String title, String startTime, String endTime, String date, Boolean state) {
        this.userId = userId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.state = state;
    }

    public Task(String id, String userId, String title, String startTime, String endTime, String date, Boolean state) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
