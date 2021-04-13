package com.isen.math_hunt.entities;

import java.util.List;

public class Progression {

    private String _id;
    private String geoGroupId;
    private String geoGroupName;
    private int geoGroupScore;
    private int geoGroupScoreValue;


    public class enigmaProgression{
        private boolean done;
        private int score;
        private List<String> usedHintsIds;
        private String _id;
        private String enigmaId;
        private String enigmaName;
        private int scoreValue;

        public enigmaProgression(boolean done, int score, List<String> usedHintsIds, String _id, String enigmaId, String enigmaName, int scoreValue) {
            this.done = done;
            this.score = score;
            this.usedHintsIds = usedHintsIds;
            this._id = _id;
            this.enigmaId = enigmaId;
            this.enigmaName = enigmaName;
            this.scoreValue = scoreValue;
        }

        public boolean isDone() {
            return done;
        }

        public void setDone(boolean done) {
            this.done = done;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public List<String> getUsedHintsIds() {
            return usedHintsIds;
        }

        public void setUsedHintsIds(List<String> usedHintsIds) {
            this.usedHintsIds = usedHintsIds;
        }

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getEnigmaId() {
            return enigmaId;
        }

        public void setEnigmaId(String enigmaId) {
            this.enigmaId = enigmaId;
        }

        public String getEnigmaName() {
            return enigmaName;
        }

        public void setEnigmaName(String enigmaName) {
            this.enigmaName = enigmaName;
        }

        public int getScoreValue() {
            return scoreValue;
        }

        public void setScoreValue(int scoreValue) {
            this.scoreValue = scoreValue;
        }
    }

}