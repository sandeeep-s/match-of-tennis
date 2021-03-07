package com.tennis.match.domain.model;

public interface GameScoringRules {
    GameScore calculatePlayerScore(GameScore currentScore, GameScore otherPlayerScore);
}
