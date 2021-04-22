package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;
import com.isen.math_hunt.entities.Enigma;

import java.util.List;

public class EnigmaList {
    @SerializedName("enigmas")
    private List<Enigma> enigmas;
    public String count;

    public EnigmaList(List<Enigma> enigmas, String count) {
        this.enigmas = enigmas;
        this.count = count;
    }

    public List<Enigma> getEnigmas() {
        return enigmas;
    }

    public void setEnigmas(List<Enigma> enigmas) {
        this.enigmas = enigmas;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
