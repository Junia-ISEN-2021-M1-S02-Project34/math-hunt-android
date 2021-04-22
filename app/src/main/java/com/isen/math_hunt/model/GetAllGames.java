package com.isen.math_hunt.model;

import com.google.gson.annotations.SerializedName;
import com.isen.math_hunt.entities.Game;

import java.util.List;

public class GetAllGames {
    @SerializedName("games")
    private List<Game> games;

    public GetAllGames(List<Game> games) {
        this.games = games;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
