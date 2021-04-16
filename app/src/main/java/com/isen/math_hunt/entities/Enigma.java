package com.isen.math_hunt.entities;

/**
 * Enigma entity class
 */

import com.google.gson.annotations.SerializedName;


public class Enigma {

    @SerializedName("_id")
    private String _id;
    @SerializedName("name")
    private String name;
    @SerializedName("description")
    private String description;
    @SerializedName("question")
    private String question;
    @SerializedName("positionX")
    private Number positionX;
    @SerializedName("positionY")
    private Number positionY;
    @SerializedName("scoreValue")
    private int scoreValue;
    @SerializedName("isActive")
    private boolean isActive;
    @SerializedName("isLinked")
    private boolean isLinked;
    @SerializedName("nextEnigmaId")
    private int nextEnigmaId;

    public Enigma(String _id, String name, String description, String question, Number positionX, Number positionY, int scoreValue, boolean isActive, boolean isLinked, int nextEnigmaId) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.question = question;
        this.positionX = positionX;
        this.positionY = positionY;
        this.scoreValue = scoreValue;
        this.isActive = isActive;
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

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Number getPositionX() {
        return positionX;
    }

    public void setPositionX(Number positionX) {
        this.positionX = positionX;
    }

    public Number getPositionY() {
        return positionY;
    }

    public void setPositionY(Number positionY) {
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
}
