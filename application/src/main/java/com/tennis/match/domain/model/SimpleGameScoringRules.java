package com.tennis.match.domain.model;

public class SimpleGameScoringRules implements GameScoringRules {

    @Override
    public void addPointToServerScore(Game game) {
        Points updatedPoints = game.serverScore().points().nextPoint();
        if (updatedPoints == Points.ADVANTAGE){
            updatedPoints = updatedPoints.nextPoint();
        }
        game.setServerScore(GameScore.of(updatedPoints));

        if (isDeuce(game)){
            game.setGameScoringRules(new DeuceGameScoringRules());
        }
    }

    @Override
    public void addPointToReceiverScore(Game game) {
        Points updatedPoints = game.receiverScore().points().nextPoint();
        if (updatedPoints == Points.ADVANTAGE){
            updatedPoints = updatedPoints.nextPoint();
        }
        game.setReceiverScore(GameScore.of(updatedPoints));

        if (isDeuce(game)){
            game.setGameScoringRules(new DeuceGameScoringRules());
        }
    }

    @Override
    public boolean isDeuce(Game game) {
        return game.serverScore().points() == Points.FORTY && game.receiverScore().points() == Points.FORTY;
    }

}
