package com.example.liu.androidtest;

/**
 * Created by liu on 15/11/17.
 */

public class Talk {

    private Integer time;
    private String title;
    private String startTime;

    public Talk(){
        time = 0;
        startTime="00:00";
    }

    public Integer getTime() {
        return time;
    }
    public void setTime(Integer time) {
        this.time = time;
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

    @Override
    public String toString() {
        String schedule = startTime + " "+ title+" "+ "\n";
        return schedule;
    }

}
