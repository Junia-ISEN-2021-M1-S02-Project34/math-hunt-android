package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;

public class HintId {

    @SerializedName("hintId")
    private String hintId;

    public HintId(String hintId) {
        this.hintId = hintId;
    }

    public String getHintId() {
        return hintId;
    }

    public void setHintId(String hintId) {
        this.hintId = hintId;
    }
}
