package com.tennis.match.domain.model;

public class GameScore {

    private Points pointsWon;

    public GameScore() {
    }

    public String value() {
        return pointsWon.pointsScore();
    }

    public void initialize() {
        pointsWon = Points.ZERO;
    }

    public void addPoint() {
        pointsWon = pointsWon.nextPoint();
    }
}
