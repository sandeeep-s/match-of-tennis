package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class Game {

    private GameId gameId;
    private TennisMatchSet partOfSet;
    private GameScore playerOneScore;
    private GameScore playerTwoScore;
    private GameRules gameRules;

    public static Game from(GameId gameId, TennisMatchSet set) {
        return new Game(gameId, set);
    }

    public void start() {
        playerOneScore.initialize();
        playerTwoScore.initialize();
    }

    public void playerOneWinsPoint() {
        playerOneScore.addPoint();
    }

    public void playerTwoWinsPoint() {
        playerTwoScore.addPoint();
    }

    public GameId gameId() {
        return getGameId();
    }

    public TennisMatchSet partOfSet() {
        return getPartOfSet();
    }

    public GameScore playerOneScore() {
        return getPlayerOneScore();
    }

    public GameScore playerTwoScore() {
        return getPlayerTwoScore();
    }

    public GameRules gameRules() {
        return getGameRules();
    }

    private Game(GameId gameId, TennisMatchSet set) {
        setGameId(gameId);
        setPartOfSet(set);
        setPlayerOneScore(new GameScore());
        setPlayerTwoScore(new GameScore());
        setGameRules(new SimpleGameRules());
    }
    private void setGameId(GameId gameId) {
        this.gameId = gameId;
    }

    private void setPartOfSet(TennisMatchSet set) {
        this.partOfSet = set;
    }

    private void setPlayerOneScore(GameScore playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    private void setPlayerTwoScore(GameScore playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    public void setGameRules(GameRules gameRules) {
        this.gameRules = gameRules;
    }
}
