package com.isen.math_hunt.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EnigmasProgression {

    @SerializedName("done")
    private boolean done;
    @SerializedName("score")
    private int score;
    @SerializedName("usedHintsIds")
    private List<String> usedHintsIds;
    @SerializedName("_id")
    private String _id;
    @SerializedName("enigmaId")
    private String enigmaId;
    @SerializedName("enigmaName")
    private String enigmaName;
    @SerializedName("scoreValue")
    private int scoreValue;

    @SerializedName("attemptsNumber")
    private int attemptsNumber;

    public EnigmasProgression(boolean done, int score, List<String> usedHintsIds, String _id, String enigmaId, String enigmaName, int scoreValue, int attemptsNumber) {
        this.done = done;
        this.score = score;
        this.usedHintsIds = usedHintsIds;
        this._id = _id;
        this.enigmaId = enigmaId;
        this.enigmaName = enigmaName;
        this.scoreValue = scoreValue;
        this.attemptsNumber = attemptsNumber;
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

    public String getEnigmaName() {
        return enigmaName;
    }

    public void setEnigmaName(String enigmaName) {
        this.enigmaName = enigmaName;
    }

    public int getScoreValue() {
        return scoreValue;
    }

    public void setScoreValue(int scoreValue) {
        this.scoreValue = scoreValue;
    }

    public int getAttemptsNumber() {
        return attemptsNumber;
    }

    public void setAttemptsNumber(int attemptsNumber) {
        this.attemptsNumber = attemptsNumber;
    }
}