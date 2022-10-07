package com.android.todoapp.model;

import java.io.Serializable;

public class Task implements Serializable {
    private String title;
    private String startTime;
    private String endTime;
    private String detail;
    private Boolean state;

    public Task(String title, String startTime, String endTime, String detail, Boolean state) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.detail = detail;
        this.state = state;
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Boolean isCompleted() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }
}
