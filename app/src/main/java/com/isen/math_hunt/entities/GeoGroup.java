package com.isen.math_hunt.entities;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * GeoGroup entity class
 */
public class GeoGroup {

    @SerializedName("_id")
    private String _id;

    @SerializedName("positionY")
    private Number positionY;

    @SerializedName("positionX")
    private Number positionX;

    @SerializedName("name")
    private String name;

    @SerializedName("radius")
    private Number radius;

    @SerializedName("pictureUrl")
    private String pictureUrl;



    public GeoGroup(String _id, Number positionY, Number positionX, String name, Number radius, String pictureUrl) {
        this._id = _id;
        this.positionY = positionY;
        this.positionX = positionX;
        this.name = name;
        this.radius = radius;
        this.pictureUrl = pictureUrl;

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Number getPositionY() {
        return positionY;
    }

    public void setPositionY(Number positionY) {
        this.positionY = positionY;
    }

    public Number getPositionX() {
        return positionX;
    }

    public void setPositionX(Number positionX) {
        this.positionX = positionX;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getRadius() {
        return radius;
    }

    public void setRadius(Number radius) {
        this.radius = radius;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

}

