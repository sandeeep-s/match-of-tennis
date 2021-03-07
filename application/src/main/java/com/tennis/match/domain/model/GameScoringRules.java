package com.tennis.match.domain.model;

public interface GameScoringRules {

    void addPointToServerScore(Game game);

    void addPointToReceiverScore(Game game);

    boolean isDeuce(Game game);
}
