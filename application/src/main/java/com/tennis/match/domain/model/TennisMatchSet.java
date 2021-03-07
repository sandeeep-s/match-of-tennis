package com.tennis.match.domain.model;

import lombok.Builder;

import java.util.List;

@Builder
public class TennisMatchSet {

    private List<Game> games;
    private Game gameInProgress;

    public Game startNewGame() {
        return Game.from(newGameId(), this);
    }

    private GameId newGameId() {
        return GameId.from(1);
    }
}
