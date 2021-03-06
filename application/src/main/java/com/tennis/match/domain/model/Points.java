package com.tennis.match.domain.model;

public enum Points {

    ZERO("0"),
    One("15"),
    Two("30"),
    THREE("40"),
    FOUR("WinGame");

    private String pointsScore;

    Points(String pointsScore) {
        this.pointsScore = pointsScore;
    }

    public String pointsScore() {
        return pointsScore;
    }
}
