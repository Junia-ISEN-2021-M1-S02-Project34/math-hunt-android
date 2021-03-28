package com.isen.math_hunt.entities;

public class Hint {

    private String name;
    private String text;
    private int rank;
    private int penalty;
    private String propositionToRemove;
    private String enigmaId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public String getPropositionToRemove() {
        return propositionToRemove;
    }

    public void setPropositionToRemove(String propositionToRemove) {
        this.propositionToRemove = propositionToRemove;
    }

    public String getEnigmaId() {
        return enigmaId;
    }

    public void setEnigmaId(String enigmaId) {
        this.enigmaId = enigmaId;
    }

    public Hint(String name, String text, int penalty) {
        this.name = name;
        this.text = text;
        this.penalty = penalty;
    }
}
