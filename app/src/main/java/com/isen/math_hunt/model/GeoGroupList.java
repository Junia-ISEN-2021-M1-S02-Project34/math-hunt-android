package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.GeoGroup;

import java.util.List;

public class GeoGroupList {
    @SerializedName("geoGroups")
    private List<GeoGroup> geoGroups;
    public String count;

    public GeoGroupList(List<GeoGroup> geoGroups, String count) {
        this.geoGroups = geoGroups;
        this.count = count;
    }

    public List<GeoGroup> getGeoGroups() {
        return geoGroups;
    }

    public void setGeoGroups(List<GeoGroup> geoGroups) {
        this.geoGroups = geoGroups;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}