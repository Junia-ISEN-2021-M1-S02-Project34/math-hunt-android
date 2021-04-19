package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;

public class GetGameById {
    @SerializedName("isStarted")
    private boolean isStarted;
    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;
    @SerializedName("duration")
    private int duration;

    public GetGameById(boolean isStarted, String _id, String name, int duration) {
        this.isStarted = isStarted;
        this._id = _id;
        this.name = name;
        this.duration = duration;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
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
}
