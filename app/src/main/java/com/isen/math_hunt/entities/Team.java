package com.isen.math_hunt.entities;

public class Team {
    private int _id;
    private String username;
    private String password;
    private int score;
    private String gameId;
    private String currentId;
    private String enigmaList;

    public Team(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getCurrentId() {
        return currentId;
    }

    public void setCurrentId(String currentId) {
        this.currentId = currentId;
    }

    public String getEnigmaList() {
        return enigmaList;
    }

    public void setEnigmaList(String enigmaList) {
        this.enigmaList = enigmaList;
    }
}
