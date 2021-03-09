package com.tennis.match.domain.model;

public class SetScoringRules {

    public void scoreGameFor(PlayerNumber playerNumber, Game game) {
        game.parentSet().addToGamesWonBy(playerNumber, game);

        if (game.parentSet().scoreOf(playerNumber) == 6 && game.parentSet().scoreOf(playerNumber.oppositePlayerNumber()) <= 4){
            game.parentSet().makeWinner(playerNumber);
        }
    }
}
