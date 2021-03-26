package com.isen.math_hunt;

public class Rank {
    private int mRank;
    private String mTeamName;
    private int mPoints;

    // Constructor that is used to create an instance of the Rank object

    public Rank(int rank, String teamName, int points) {
        this.mRank = rank;
        this.mTeamName = teamName;
        this.mPoints = points;
    }

    public int getRank() {
        return mRank;
    }

    public void setRank(int rank) {
        mRank = rank;
    }

    public String getTeamName() {
        return mTeamName;
    }

    public void setTeamName(String teamName) {
        mTeamName = teamName;
    }

    public int getPoints() {
        return mPoints;
    }

    public void setPoints(int points) {
        mPoints = points;
    }
}
