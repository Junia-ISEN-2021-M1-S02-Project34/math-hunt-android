package com.isen.math_hunt.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Progression {

    @SerializedName("_id")
    private String _id;
    @SerializedName("geoGroupId")
    private String geoGroupId;
    @SerializedName("geoGroupName")
    private String geoGroupName;
    @SerializedName("geoGroupScore")
    private int geoGroupScore;
    @SerializedName("geoGroupScoreValue")
    private int geoGroupScoreValue;
    @SerializedName("enigmasProgression")
    private List<EnigmasProgression> enigmasProgression;

    public Progression(String _id, String geoGroupId, String geoGroupName, int geoGroupScore, int geoGroupScoreValue, List<EnigmasProgression> enigmasProgression) {
        this._id = _id;
        this.geoGroupId = geoGroupId;
        this.geoGroupName = geoGroupName;
        this.geoGroupScore = geoGroupScore;
        this.geoGroupScoreValue = geoGroupScoreValue;
        this.enigmasProgression = enigmasProgression;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getGeoGroupId() {
        return geoGroupId;
    }

    public void setGeoGroupId(String geoGroupId) {
        this.geoGroupId = geoGroupId;
    }

    public String getGeoGroupName() {
        return geoGroupName;
    }

    public void setGeoGroupName(String geoGroupName) {
        this.geoGroupName = geoGroupName;
    }

    public int getGeoGroupScore() {
        return geoGroupScore;
    }

    public void setGeoGroupScore(int geoGroupScore) {
        this.geoGroupScore = geoGroupScore;
    }

    public int getGeoGroupScoreValue() {
        return geoGroupScoreValue;
    }

    public void setGeoGroupScoreValue(int geoGroupScoreValue) {
        this.geoGroupScoreValue = geoGroupScoreValue;
    }

    public List<EnigmasProgression> getEnigmasProgression() {
        return enigmasProgression;
    }

    public void setEnigmasProgression(List<EnigmasProgression> enigmasProgression) {
        this.enigmasProgression = enigmasProgression;
    }
}