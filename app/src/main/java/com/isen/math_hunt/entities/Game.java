package com.isen.math_hunt.entities;

import java.util.Date;

public class Game {

    private String name;
    private Date startDate;
    private int duration;
    private boolean isStarted;

    public Game(String name, Date startDate, int duration, boolean isStarted) {
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
        this.isStarted = isStarted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }
}
