package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.Player;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenPlayerWinsPoint_theGame {

    @Test
    void shouldChangeScoreToFifteen_given_currentScoreWasZero(){
        Player playerOne = new Player();
        Player playerTwo = new Player();
        Game game = new Game(playerOne, playerTwo);
        game.start();
        assertThat(game.playerOneScore().value()).isEqualTo("0");

        game.playerOneWinsPoint();

        assertThat(game.playerOneScore().value()).isEqualTo("15");
    }
}
