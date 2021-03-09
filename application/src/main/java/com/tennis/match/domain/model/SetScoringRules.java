package com.tennis.match.domain.model;

public class SetScoringRules {

    public void scoreGameFor(PlayerNumber playerNumber, Game game) {
        game.partOfSet().addToGamesWonBy(playerNumber, game);
    }
}
