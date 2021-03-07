package com.tennis.match.domain.model;

public class GameScore {

    private Points pointsWon;

    public Points pointsWon() {
        return pointsWon;
    }

    public void initialize() {
        pointsWon = Points.LOVE;
    }

    public void addPoint() {
        pointsWon = pointsWon.nextPoint(false);
    }
}
