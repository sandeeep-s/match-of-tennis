package com.tennis.match.domain.model;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.ADVANTAGE;
import static com.tennis.match.domain.model.Points.GAME;

public class DeuceGameScoringRules implements GameScoringRules {

    @Override
    public void scorePoint(Game game, PlayerNumber playerNumber) {
        PlayerNumber oppositePlayerNumber = playerNumber.oppositePlayerNumber();
        if(game.scoreOf(oppositePlayerNumber) == ADVANTAGE){
            game.updateScoreOf(oppositePlayerNumber, game.scoreOf(oppositePlayerNumber).prevPoint());
        }else{
            game.updateScoreOf(playerNumber, game.scoreOf(playerNumber).nextPoint());
        }

        if (game.scoreOf(playerNumber) == GAME){
            game.partOfSet().scoreGameWonBy(playerNumber, game);
        }
    }

    @Override
    public boolean isDeuce(Game game) {
        return game.scoreOf(PLAYER_ONE) == game.scoreOf(PLAYER_TWO);
    }
}
