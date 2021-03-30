package com.isen.math_hunt.entities;

/**
 * Enigma entity class
 */

import com.google.gson.annotations.SerializedName;


public class Enigma {

    @SerializedName("id")
    private String _id;
    @SerializedName("id")
    private String name;
    private String description;
    private String pictureUrl;
    private String question;
    private String positionX;
    private String positionY;
    private int scoreValue;
    private boolean isActive;
    private GeoGroup geoGroupId;
    private boolean isLinked;
    private int nextEnigmaId;

    public Enigma(String name, String description, String pictureUrl, String question, String positionX, String positionY, int scoreValue, boolean isActive, GeoGroup geoGroup, boolean isLinked, int nextEnigmaId) {
        this.name = name;
        this.description = description;
        this.pictureUrl = pictureUrl;
        this.question = question;
        this.positionX = positionX;
        this.positionY = positionY;
        this.scoreValue = scoreValue;
        this.isActive = isActive;
        this.geoGroupId = geoGroup;
        this.isLinked = isLinked;
        this.nextEnigmaId = nextEnigmaId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getPositionX() {
        return positionX;
    }

    public void setPositionX(String positionX) {
        this.positionX = positionX;
    }

    public String getPositionY() {
        return positionY;
    }

    public void setPositionY(String positionY) {
        this.positionY = positionY;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public GeoGroup getGeoGroupId() {
        return geoGroupId;
    }

    public void setGeoGroupId(GeoGroup geoGroupId) {
        this.geoGroupId = geoGroupId;
    }

    public boolean isLinked() {
        return isLinked;
    }

    public void setLinked(boolean linked) {
        isLinked = linked;
    }

    public int getNextEnigmaId() {
        return nextEnigmaId;
    }

    public void setNextEnigmaId(int nextEnigmaId) {
        this.nextEnigmaId = nextEnigmaId;
    }

    public Enigma(String name, int scoreValue) {
        this.name = name;
        this.scoreValue = scoreValue;
    }
}
