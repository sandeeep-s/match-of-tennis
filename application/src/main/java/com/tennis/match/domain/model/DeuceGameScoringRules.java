package com.tennis.match.domain.model;

public class DeuceGameScoringRules implements GameScoringRules {

    @Override
    public void addPointToServerScore(Game game) {
        if(game.receiverScore().points() == Points.ADVANTAGE){
            game.setReceiverScore(GameScore.of(game.receiverScore().points().prevPoint()));
        }else{
            game.setServerScore(GameScore.of(game.serverScore().points().nextPoint()));
        }
    }

    @Override
    public void addPointToReceiverScore(Game game) {
        if(game.serverScore().points() == Points.ADVANTAGE){
            game.setServerScore(GameScore.of(game.serverScore().points().prevPoint()));
        }else{
            game.setReceiverScore(GameScore.of(game.receiverScore().points().nextPoint()));
        }
    }

    @Override
    public boolean isDeuce(Game game) {
        return game.serverScore().points() == game.receiverScore().points();
    }
}
