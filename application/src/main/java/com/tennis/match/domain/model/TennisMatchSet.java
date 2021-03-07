package com.tennis.match.domain.model;

import lombok.Builder;

import java.util.List;

@Builder
public class TennisMatchSet {

    private List<Game> games;
    private Game gameInProgress;

    public void startNewGame() {
        Game game = Game.from(newGameId(), this);
        game.start();
    }

    private GameId newGameId() {
        return GameId.from(1);
    }
}
