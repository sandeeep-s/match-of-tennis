package com.tennis.match.domain.model;

public interface GameScoringRules {

    void scorePoint(Game game, PlayerNumber playerNumber);

    boolean isDeuce(Game game);
}
