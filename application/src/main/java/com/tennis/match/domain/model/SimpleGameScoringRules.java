package com.tennis.match.domain.model;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.FORTY;
import static com.tennis.match.domain.model.Points.GAME;

public class SimpleGameScoringRules implements GameScoringRules {

    @Override
    public void scorePoint(Game game, PlayerNumber playerNumber) {

        Points newScore = calculateNewScore(game, playerNumber);
        game.updateScoreOf(playerNumber, newScore);

        if (isDeuce(game)) {
            game.setGameScoringRules(new DeuceGameScoringRules());
        }

        if (newScore == GAME) {
            game.parentSet().scoreGameWonBy(playerNumber, game);
        }

    }

    private Points calculateNewScore(Game game, PlayerNumber playerNumber) {

        Points updatedPoints = game.scoreOf(playerNumber).nextPoint();

        if (game.scoreOf(playerNumber) == FORTY) {
            updatedPoints = updatedPoints.nextPoint();
        }

        return updatedPoints;
    }

    @Override
    public boolean isDeuce(Game game) {

        return game.scoreOf(PLAYER_ONE) == FORTY
                && game.scoreOf(PLAYER_TWO) == FORTY;
    }

}
