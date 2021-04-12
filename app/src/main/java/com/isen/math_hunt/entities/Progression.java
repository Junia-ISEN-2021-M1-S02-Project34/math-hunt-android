package com.isen.math_hunt.entities;

import java.util.List;

public class Progression {

    private boolean done;
    private int score;
    private List<String> usedHintsIds;
    private String _id;
    private String enigmaId;
    private String geoGroupId;

    public Progression(boolean done, int score, List<String> usedHintsIds, String _id, String enigmaId, String geoGroupId) {
        this.done = done;
        this.score = score;
        this.usedHintsIds = usedHintsIds;
        this._id = _id;
        this.enigmaId = enigmaId;
        this.geoGroupId = geoGroupId;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getUsedHintsIds() {
        return usedHintsIds;
    }

    public void setUsedHintsIds(List<String> usedHintsIds) {
        this.usedHintsIds = usedHintsIds;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getEnigmaId() {
        return enigmaId;
    }

    public void setEnigmaId(String enigmaId) {
        this.enigmaId = enigmaId;
    }

    public String getGeoGroupId() {
        return geoGroupId;
    }

    public void setGeoGroupId(String geoGroupId) {
        this.geoGroupId = geoGroupId;
    }
}
