package com.tennis.match.domain.model;

public class Game {

    private GameScore playerOneScore;
    private GameScore playerTwoScore;

    public Game(Player playerOne, Player playerTwo) {
        playerOneScore = new GameScore(playerOne);
        playerTwoScore = new GameScore(playerTwo);
    }

    public void start() {
        playerOneScore.initialize();
        playerTwoScore.initialize();
    }

    public GameScore playerOneScore() {
        return playerOneScore;
    }

    public GameScore playerTwoScore() {
        return playerTwoScore;
    }

    public void playerOneWinsPoint() {
        playerOneScore.addPoint();
    }
}
