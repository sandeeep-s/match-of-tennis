package com.tennis.match.domain.model;

public class DeuceGameScoringRules implements GameScoringRules {
    @Override
    public GameScore calculatePlayerScore(GameScore currentScore, GameScore otherPlayerScore) {
        return null;
    }

    @Override
    public GameScore calculateServerScore(Game game) {
        return null;
    }
}
