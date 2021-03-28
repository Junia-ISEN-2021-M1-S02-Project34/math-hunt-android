package com.isen.math_hunt.entities;

/**
 * Enigma entity class
 */
public class Enigma {

    private int _id;
    private String name;
    private String description;
    private String question;
    private String positionX;
    private String positionY;
    private int scoreValue;
    private boolean isActive;
    private GeoGroup geoGroup;
    private boolean isLinked;
    private int nextEnigmaId;


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public GeoGroup getGeoGroup() {
        return geoGroup;
    }

    public void setGeoGroup(GeoGroup geoGroup) {
        this.geoGroup = geoGroup;
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

    public Enigma(int _id, String name, String description, String question, String positionX, String positionY, int scoreValue, boolean isActive, GeoGroup geoGroup, boolean isLinked, int nextEnigmaId) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.question = question;
        this.positionX = positionX;
        this.positionY = positionY;
        this.scoreValue = scoreValue;
        this.isActive = isActive;
        this.geoGroup = geoGroup;
        this.isLinked = isLinked;
        this.nextEnigmaId = nextEnigmaId;
    }

    public Enigma(String name, int scoreValue) {
        this.name = name;
        this.scoreValue = scoreValue;
    }
}
