package com.isen.math_hunt.model;

import java.util.List;

public class ProgressionPost {

    private String finishedEnigma;
    private boolean isSuccess;

    public ProgressionPost(String finishedEnigma, boolean isSuccess) {
        this.finishedEnigma = finishedEnigma;
        this.isSuccess = isSuccess;
    }

    public String getFinishedEnigma() {
        return finishedEnigma;
    }

    public void setFinishedEnigma(String finishedEnigma) {
        this.finishedEnigma = finishedEnigma;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
