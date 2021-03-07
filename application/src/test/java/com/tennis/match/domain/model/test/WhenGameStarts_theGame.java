package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenGameStarts_theGame {

    @Test
    void shouldStartWithScoreOfZeroPointForEachPlayer() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);

        game.start();

        assertThat(game.playerOneScore().pointsWon()).isEqualTo("0");
        assertThat(game.playerTwoScore().pointsWon()).isEqualTo("0");
    }

    @Test
    void shouldActivateSimpleGameRules() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);

        game.start();

        assertThat(game.gameRules()).isInstanceOf(SimpleGameRules.class);
    }


}
