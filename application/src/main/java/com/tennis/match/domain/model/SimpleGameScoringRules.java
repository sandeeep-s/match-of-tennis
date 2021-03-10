package com.tennis.match.domain.model;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.FORTY;
import static com.tennis.match.domain.model.Points.GAME;

public class SimpleGameScoringRules implements GameScoringRules {

    @Override
    public Points calculateNewScoreOf(PlayerNumber playerNumber, Game game) {

        Points updatedPoints = game.scoreOf(playerNumber).nextPoint();

        if (game.scoreOf(playerNumber) == FORTY) {
            updatedPoints = updatedPoints.nextPoint();
        }

        return updatedPoints;
    }

    @Override
    public Points calculateNewScoreOfOpponent(PlayerNumber playerNumber, Game game) {
        return game.scoreOf(playerNumber.opponent());
    }

    @Override
    public boolean isDeuce(Game game) {

        return game.scoreOf(PLAYER_ONE) == FORTY
                && game.scoreOf(PLAYER_TWO) == FORTY;
    }

}
