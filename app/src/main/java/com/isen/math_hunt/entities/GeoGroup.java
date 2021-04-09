package com.isen.math_hunt.entities;


import java.util.List;

/**
 * GeoGroup entity class
 */
public class GeoGroup {

    private String _id;
    private String name;
    private double positionX;
    private double positionY;
    private double radius;
    private String pictureUrl;

    public GeoGroup(String _id, String name, double positionX, double positionY, double radius, String pictureUrl) {
        this._id = _id;
        this.name = name;
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
        this.pictureUrl = pictureUrl;
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

    public double getPositionX() {
        return positionX;
    }

    public void setPositionX(double positionX) {
        this.positionX = positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public void setPositionY(double positionY) {
        this.positionY = positionY;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }
}
