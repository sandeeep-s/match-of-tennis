package com.tennis.match.domain.model;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.GAME;

public class SetScoringRules{

    public boolean hasWonSet(PlayerNumber playerNumber, TennisMatchSet tennisMatchSet) {

        return (tennisMatchSet.scoreOf(playerNumber) == 6 && tennisMatchSet.scoreOf(playerNumber.opponent()) <= 4)
                || tennisMatchSet.scoreOf(playerNumber) == 7;
    }

    public boolean hasWonGame(PlayerNumber playerNumber, Game game) {
        return game.scoreOf(playerNumber) == GAME;
    }

    public boolean isSetTied(TennisMatchSet set) {
        return set.scoreOf(PLAYER_ONE) == 6 && set.scoreOf(PLAYER_TWO) == 6;
    }

}
