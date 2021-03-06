package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.Points;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenGameStarts_theGame {

    @Test
    void shouldStartWithScoreOfZeroPointForEachPlayer(){

        Game game = new Game();

        game.start();

        assertThat(game.playerOneScore()).isEqualTo(Points.ZERO);
        assertThat(game.playerTwoScore()).isEqualTo(Points.ZERO);
    }

}
