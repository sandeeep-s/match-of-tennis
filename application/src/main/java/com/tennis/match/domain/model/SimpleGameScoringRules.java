package com.tennis.match.domain.model;

public class SimpleGameScoringRules implements GameScoringRules {

    @Override
    public GameScore calculatePlayerScore(GameScore currentScore, GameScore otherPlayerScore) {
        return null;
    }

    @Override
    public GameScore calculateServerScore(Game game) {
        GameScore currentScoreOfServer = game.serverScore();
        return GameScore.of(currentScoreOfServer.points().nextPoint());
    }

    private void checkDeuce(Game game) {
        if (game.serverScore().points() == Points.FORTY && game.serverScore().points() == Points.FORTY){
            game.setGameScoringRules(new DeuceGameScoringRules());
        }
    }

}
