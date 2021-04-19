package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;

public class AttemptsNumber {

    @SerializedName("attemptsNumber")
    private int attemptsNumber;

    public AttemptsNumber(int attemptsNumber) {
        this.attemptsNumber = attemptsNumber;
    }

    public int getAttemptsNumber() {
        return attemptsNumber;
    }

    public void setAttemptsNumber(int attemptsNumber) {
        this.attemptsNumber = attemptsNumber;
    }
}
