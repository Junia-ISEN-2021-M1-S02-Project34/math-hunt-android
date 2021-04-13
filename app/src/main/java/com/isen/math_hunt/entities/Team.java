package com.isen.math_hunt.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Team {

    @SerializedName("_id")
    private String _id;
    @SerializedName("username")
    private String username;
    @SerializedName("password")
    private String password;
    @SerializedName("score")
    private int score;
    @SerializedName("gameId")
    private String gameId;
    @SerializedName("isConnected")
    private boolean isConnected;
    @SerializedName("currentEnigmaId")
    private String currentEnigmaId;
    @SerializedName("currentGeoGroupId")
    private String currentGeoGroupId;
    @SerializedName("progression")
    private List<Progression> progression;

    public Team(String _id, String username, String password, int score, String gameId, boolean isConnected, String currentEnigmaId, String currentGeoGroupId, List<Progression> progression) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.score = score;
        this.gameId = gameId;
        this.isConnected = isConnected;
        this.currentEnigmaId = currentEnigmaId;
        this.currentGeoGroupId = currentGeoGroupId;
        this.progression = progression;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
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

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getCurrentEnigmaId() {
        return currentEnigmaId;
    }

    public void setCurrentEnigmaId(String currentEnigmaId) {
        this.currentEnigmaId = currentEnigmaId;
    }

    public String getCurrentGeoGroupId() {
        return currentGeoGroupId;
    }

    public void setCurrentGeoGroupId(String currentGeoGroupId) {
        this.currentGeoGroupId = currentGeoGroupId;
    }

    public List<Progression> getProgression() {
        return progression;
    }

    public void setProgression(List<Progression> progression) {
        this.progression = progression;
    }
}