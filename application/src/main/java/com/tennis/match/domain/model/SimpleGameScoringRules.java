package com.tennis.match.domain.model;

public class SimpleGameScoringRules implements GameScoringRules {

    @Override
    public void addPointToServerScore(Game game) {
        game.setServerScore(GameScore.of(game.serverScore().points().nextPoint()));
    }

    @Override
    public void addPointToReceiverScore(Game game) {
        game.setReceiverScore(GameScore.of(game.receiverScore().points().nextPoint()));
    }

    private void checkDeuce(Game game) {
        if (game.serverScore().points() == Points.FORTY && game.serverScore().points() == Points.FORTY){
            game.setGameScoringRules(new DeuceGameScoringRules());
        }
    }

}
