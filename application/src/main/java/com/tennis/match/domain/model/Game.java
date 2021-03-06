package com.tennis.match.domain.model;

public class Game {

    private GameScore playerOneScore;
    private GameScore playerTwoScore;

    public void start() {
    }

    public GameScore playerOneScore() {
        return playerOneScore;
    }

    public GameScore playerTwoScore() {
        return playerTwoScore;
    }
}
