package com.tennis.match.domain.model;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.ADVANTAGE;

public class DeuceGameScoringRules implements GameScoringRules {

    @Override
    public void scorePoint(Game game, PlayerNumber playerNumber) {
        PlayerNumber oppositePlayerNumber = playerNumber.oppositePlayerNumber();
        if(game.currentScoreOf(oppositePlayerNumber) == ADVANTAGE){
            game.updateScoreOf(oppositePlayerNumber, game.currentScoreOf(oppositePlayerNumber).prevPoint());
        }else{
            game.updateScoreOf(playerNumber, game.currentScoreOf(playerNumber).nextPoint());
        }
    }

    @Override
    public boolean isDeuce(Game game) {
        return game.currentScoreOf(PLAYER_ONE) == game.currentScoreOf(PLAYER_TWO);
    }
}
