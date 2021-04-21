package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;

public class RankingResponse {

    @SerializedName("score")
    private Number score;
    @SerializedName("gameIsFinished")
    private boolean gameIsFinished;
    @SerializedName("_id")
    private String _id;
    @SerializedName("userName")
    private String userName;

    public RankingResponse(Number score, boolean gameIsFinished, String _id, String userName) {
        this.score = score;
        this.gameIsFinished = gameIsFinished;
        this._id = _id;
        this.userName = userName;
    }

    public Number getScore() {
        return score;
    }

    public void setScore(Number score) {
        this.score = score;
    }

    public boolean isGameIsFinished() {
        return gameIsFinished;
    }

    public void setGameIsFinished(boolean gameIsFinished) {
        this.gameIsFinished = gameIsFinished;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
