package com.isen.math_hunt.entities;

import java.util.ArrayList;
import java.util.List;

public class Answer {

    private int _id;
    private String enigmaId;
    private boolean isMcq;
    private String solution;
    private int attemptNumber;
    private ArrayList<Proposition> propositionList;

    public Answer(boolean isMcq, ArrayList<Proposition> propositionList) {
        this.isMcq = isMcq;
        this.propositionList = propositionList;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
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

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public ArrayList<Proposition> getPropositionList() {
        return propositionList;
    }

    public void setPropositionList(ArrayList<Proposition> propositionList) {
        this.propositionList = propositionList;
    }
}
