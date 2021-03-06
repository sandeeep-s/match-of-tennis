package com.tennis.match.domain.model;

public class GameScore {

    private Player player;
    private Points pointsWon;

    public GameScore(Player player) {
        this.player = player;
    }

    public String value() {
        return pointsWon.pointsScore();
    }

    public void initialize() {
        pointsWon = Points.ZERO;
    }
}
