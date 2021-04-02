package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;
import com.isen.math_hunt.entities.Answer;
import com.isen.math_hunt.entities.Enigma;
import com.isen.math_hunt.entities.Proposition;

import java.util.List;

public class FullEnigma {

    @SerializedName("enigma")
    private Enigma enigma;
    @SerializedName("answer")
    private Answer answer;
    @SerializedName("propositions")
    private List<Proposition> proposition;

    public FullEnigma(Enigma enigma, Answer answer, List<Proposition> proposition) {
        this.enigma = enigma;
        this.answer = answer;
        this.proposition = proposition;
    }

    public Enigma getEnigma() {
        return enigma;
    }

    public void setEnigma(Enigma enigma) {
        this.enigma = enigma;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public List<Proposition> getProposition() {
        return proposition;
    }

    public void setProposition(List<Proposition> proposition) {
        this.proposition = proposition;
    }
}
