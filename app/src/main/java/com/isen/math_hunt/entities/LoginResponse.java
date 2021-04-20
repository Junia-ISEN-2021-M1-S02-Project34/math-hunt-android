package com.isen.math_hunt.entities;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("teamId")
    private String teamId;

    @SerializedName("accessToken")
    private String accessToken;

    public LoginResponse(String teamId, String accessToken) {

        this.teamId = teamId;
        this.accessToken = accessToken;

    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}

