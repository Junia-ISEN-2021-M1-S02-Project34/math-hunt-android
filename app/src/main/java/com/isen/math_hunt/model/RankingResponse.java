package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RankingResponse {

    private List<Rank> teams;

    public RankingResponse(List<Rank> teams) {
        this.teams = teams;
    }

    public List<Rank> getRankList() {
        return teams;
    }

    public void setRankList(List<Rank> teams) {
        this.teams = teams;
    }

    public class Rank{
        @SerializedName("score")
        private Number score;
        @SerializedName("gameFinished")
        private boolean gameFinished;
        @SerializedName("_id")
        private String _id;
        @SerializedName("username")
        private String username;

        public Rank(Number score, boolean gameIsFinished, String _id, String username) {
            this.score = score;
            this.gameFinished = gameIsFinished;
            this._id = _id;
            this.username = username;
        }

        public Number getScore() {
            return score;
        }

        public void setScore(Number score) {
            this.score = score;
        }

        public boolean isGameIsFinished() {
            return gameFinished;
        }

        public void setGameIsFinished(boolean gameIsFinished) {
            this.gameFinished = gameIsFinished;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getUserName() {
            return username;
        }

        public void setUserName(String userName) {
            this.username = userName;
        }
    }
}
