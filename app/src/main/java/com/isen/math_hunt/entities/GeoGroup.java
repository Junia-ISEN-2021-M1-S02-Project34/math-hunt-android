package com.isen.math_hunt.entities;


public class GeoGroup {

    private int _id;
    private String name;
    private int positionX;
    private int positionY;
    private int radius;
    private String pictureUrl;
    private int score;

    public GeoGroup(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public GeoGroup(int _id, String name, int positionX, int positionY, int radius, String pictureUrl) {
        this._id = _id;
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.pictureUrl = pictureUrl;
    }

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

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
       this.score = score;
    }
}
