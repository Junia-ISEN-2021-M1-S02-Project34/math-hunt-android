package com.isen.math_hunt.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Answer {

    @SerializedName("_id")
    private String _id;
    @SerializedName("enigmaId")
    private String enigmaId;
    @SerializedName("isMcq")
    private boolean isMcq;
    @SerializedName("solution")
    private String solution;
    @SerializedName("attemptsNumber")
    private int attemptsNumber;


    public Answer(String _id, String enigmaId, boolean isMcq, String solution, int attemptsNumber) {
        this._id = _id;
        this.enigmaId = enigmaId;
        this.isMcq = isMcq;
        this.solution = solution;
        this.attemptsNumber = attemptsNumber;
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

    public boolean isMcq() {
        return isMcq;
    }

    public void setMcq(boolean mcq) {
        isMcq = mcq;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public int getAttemptsNumber() {
        return attemptsNumber;
    }

    public void setAttemptsNumber(int attemptNumber) {
        this.attemptsNumber = attemptNumber;
    }


}
