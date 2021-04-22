package com.isen.math_hunt.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Game {

    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;

    @SerializedName("duration")
    private int duration;
    @SerializedName("isStarted")
    private boolean isStarted;

    public Game(String _id, String name, Date startDate, int duration, boolean isStarted) {
        this._id = _id;
        this.name = name;
        this.duration = duration;
        this.isStarted = isStarted;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
