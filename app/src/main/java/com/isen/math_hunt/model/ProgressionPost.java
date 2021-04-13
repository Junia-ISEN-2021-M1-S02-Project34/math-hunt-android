package com.isen.math_hunt.model;

import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.Hint;

import java.util.List;

public class ProgressionPost {

    private Enigma finishedEnigma;
    private int enigmaScore;
    private List<String> usedHintsIds;

    public ProgressionPost(Enigma finishedEnigma, int enigmaScore, List<String> usedHintsIds) {
        this.finishedEnigma = finishedEnigma;
        this.enigmaScore = enigmaScore;
        this.usedHintsIds = usedHintsIds;
    }

    public Enigma getFinishedEnigma() {
        return finishedEnigma;
    }

    public void setFinishedEnigma(Enigma finishedEnigma) {
        this.finishedEnigma = finishedEnigma;
    }

    public int getEnigmaScore() {
        return enigmaScore;
    }

    public void setEnigmaScore(int enigmaScore) {
        this.enigmaScore = enigmaScore;
    }

    public List<String> getUsedHintsIds() {
        return usedHintsIds;
    }

    public void setUsedHintsIds(List<String> usedHintsIds) {
        this.usedHintsIds = usedHintsIds;
    }
}
