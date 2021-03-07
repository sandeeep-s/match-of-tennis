package com.tennis.match.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter(AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
public class TennisMatchSet {

    private SetId setId;
    private List<Game> games;
    private Game currentGame;

    public static TennisMatchSet from(SetId setId) {
        return new TennisMatchSet(setId);
    }

    public void startNewGame() {
        Game game = Game.from(newGameId(), this);
        game.start();
        setCurrentGame(game);
    }

    public SetId setId() {
        return getSetId();
    }

    public List<Game> games() {
        return getGames();
    }

    public Game currentGame() {
        return getCurrentGame();
    }

    private GameId newGameId() {
        return GameId.from(1);
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

}
