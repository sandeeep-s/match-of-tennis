package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.Getter;

@Getter(AccessLevel.PRIVATE)
public class Game {

    private GameId gameId;
    private TennisMatchSet partOfSet;
    private GameScore playerOneScore;
    private GameScore playerTwoScore;

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


    private Game(GameId gameId, TennisMatchSet set) {
        setGameId(gameId);
        setPartOfSet(set);
        setPlayerOneScore(new GameScore());
        setPlayerTwoScore(new GameScore());
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

}
