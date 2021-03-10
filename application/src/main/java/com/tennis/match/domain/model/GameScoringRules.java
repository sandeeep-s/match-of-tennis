package com.tennis.match.domain.model;

public interface GameScoringRules {

    Points calculateNewScoreOf(PlayerNumber playerNumber, Game game);

    boolean isDeuce(Game game);

    Points calculateNewScoreOfOpponent(PlayerNumber playerNumber, Game game);
}
