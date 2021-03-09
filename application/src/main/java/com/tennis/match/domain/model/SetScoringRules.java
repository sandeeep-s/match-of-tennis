package com.tennis.match.domain.model;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;

public class SetScoringRules{

    public void scoreGameFor(PlayerNumber playerNumber, Game game) {
        game.parentSet().addToGamesWonBy(playerNumber, game);

        if (game.parentSet().scoreOf(playerNumber) == 6) {
            if (game.parentSet().scoreOf(playerNumber.opponent()) <= 4) {
                game.parentSet().awardSetTo(playerNumber);
            }
        }

        if (game.parentSet().scoreOf(playerNumber) == 7) {
            game.parentSet().awardSetTo(playerNumber);
        }
    }

    public boolean isSetTied(TennisMatchSet set) {
        return set.scoreOf(PLAYER_ONE) == 6 && set.scoreOf(PLAYER_TWO) == 6;
    }

}
