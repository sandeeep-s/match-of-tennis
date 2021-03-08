package com.tennis.match.domain.model;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.Points.FORTY;

public class SimpleGameScoringRules implements GameScoringRules {

    @Override
    public void scorePoint(Game game, PlayerNumber playerNumber) {

        game.updateScoreOf(playerNumber, calculateNewScore(game, playerNumber));

        if (isDeuce(game)) {
            game.setGameScoringRules(new DeuceGameScoringRules());
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
                && game.scoreOf(PLAYER_ONE) == FORTY;
    }

}
