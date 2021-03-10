package com.tennis.match.domain.model;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.ADVANTAGE;
import static com.tennis.match.domain.model.Points.GAME;

public class DeuceGameScoringRules implements GameScoringRules {

    @Override
    public Points calculateNewScoreOf(PlayerNumber playerNumber, Game game) {
        if(game.scoreOf(playerNumber.opponent()) == ADVANTAGE) {
            return game.scoreOf(playerNumber);
        }
        else{
            return game.scoreOf(playerNumber).nextPoint();
        }
    }

    @Override
    public Points calculateNewScoreOfOpponent(PlayerNumber playerNumber, Game game) {
        if(game.scoreOf(playerNumber.opponent()) == ADVANTAGE) {
            return game.scoreOf(playerNumber.opponent()).prevPoint();
        }
        else{
            return game.scoreOf(playerNumber.opponent());
        }
    }

    @Override
    public boolean isDeuce(Game game) {
        return game.scoreOf(PLAYER_ONE) == game.scoreOf(PLAYER_TWO);
    }
}
