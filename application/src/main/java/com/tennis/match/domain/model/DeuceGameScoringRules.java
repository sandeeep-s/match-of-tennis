package com.tennis.match.domain.model;

public class DeuceGameScoringRules implements GameScoringRules {

    @Override
    public void addPointToServerScore(Game game) {
        Points updatedPoints = game.serverScore().points().nextPoint();
        game.setServerScore(GameScore.of(updatedPoints));
    }

    @Override
    public void addPointToReceiverScore(Game game) {
        Points updatedPoints = game.receiverScore().points().nextPoint();
        game.setReceiverScore(GameScore.of(updatedPoints));
    }

    @Override
    public boolean isDeuce(Game game) {
        return game.serverScore().points() == game.receiverScore().points();
    }
}
