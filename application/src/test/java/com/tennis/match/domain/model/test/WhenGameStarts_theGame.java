package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.Player;
import com.tennis.match.domain.model.Points;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenGameStarts_theGame {

    @Test
    void shouldStartWithScoreOfZeroPointForEachPlayer() {

        Player playerOne = new Player();
        Player playerTwo = new Player();
        Game game = new Game(playerOne, playerTwo);

        game.start();

        assertThat(game.playerOneScore().value()).isEqualTo("0");
        assertThat(game.playerTwoScore().value()).isEqualTo("0");
    }

}
