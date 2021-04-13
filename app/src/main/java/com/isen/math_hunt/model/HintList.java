package com.isen.math_hunt.model;

import com.isen.math_hunt.entities.Hint;

import java.util.List;

public class HintList {
    private List<Hint> hints;

    public HintList(List<Hint> hints) {
        this.hints = hints;
    }

    public List<Hint> getHints() {
        return hints;
    }

    public void setHints(List<Hint> hints) {
        this.hints = hints;
    }
}
