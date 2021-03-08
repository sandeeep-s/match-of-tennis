package com.tennis.match.domain.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PRIVATE)
public class TennisMatchSet {

    private SetId setId;
    private List<Game> games;
    private Game currentGame;
    private SetScore playerOneScore;
    private SetScore playerTwoScore;

    public static TennisMatchSet from(SetId setId) {
        return new TennisMatchSet(setId);
    }

    public void startNewGame() {
        Game game = Game.from(newGameId(), this);
        games.add(game);
        setCurrentGame(game);
    }

    public SetId setId() {
        return getSetId();
    }

    public List<Game> games() {
        return getGames();
    }

    public SetScore playerOneScore() {
        return getPlayerOneScore();
    }

    public SetScore playerTwoScore() {
        return getPlayerTwoScore();
    }

    public Game currentGame() {
        return getCurrentGame();
    }

    private GameId newGameId() {
        return GameId.from(getGames().size() + 1);
    }

    private TennisMatchSet(SetId setId) {
        setSetId(setId);
        setGames(new ArrayList<>());
    }

    private void setSetId(SetId setId) {
        this.setId = setId;
    }

    private void setGames(List<Game> games) {
        this.games = games;
    }

    private void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void setPlayerOneScore(SetScore playerOneScore) {
        this.playerOneScore = playerOneScore;
    }

    public void setPlayerTwoScore(SetScore playerTwoScore) {
        this.playerTwoScore = playerTwoScore;
    }

    private List<Game> getGames() {
        return games != null ? games : new ArrayList<>();
    }
}
