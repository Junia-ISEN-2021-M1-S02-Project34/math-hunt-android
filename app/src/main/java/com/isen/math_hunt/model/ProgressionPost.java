package com.isen.math_hunt.model;

import java.util.List;

public class ProgressionPost {

    private String finishedEnigma;
    private int enigmaScore;

    public ProgressionPost(String finishedEnigma, int enigmaScore) {
        this.finishedEnigma = finishedEnigma;
        this.enigmaScore = enigmaScore;
    }

    public String getFinishedEnigma() {
        return finishedEnigma;
    }

    public void setFinishedEnigma(String finishedEnigma) {
        this.finishedEnigma = finishedEnigma;
    }

    public int getEnigmaScore() {
        return enigmaScore;
    }

    public void setEnigmaScore(int enigmaScore) {
        this.enigmaScore = enigmaScore;
    }


}
