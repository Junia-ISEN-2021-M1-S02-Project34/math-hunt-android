package com.isen.math_hunt.entities;

public class Proposition {

    private int _id;
    private String text;
    private String answerId;

    public Proposition(int _id, String text, String answerId) {
        this._id = _id;
        this.text = text;
        this.answerId = answerId;
    }


    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }
}
