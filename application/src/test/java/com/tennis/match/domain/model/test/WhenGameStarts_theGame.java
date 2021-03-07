package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.GameId;
import com.tennis.match.domain.model.Player;
import com.tennis.match.domain.model.TennisMatchSet;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenGameStarts_theGame {

    @Test
    void shouldStartWithScoreOfZeroPointForEachPlayer() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();

        Game game = Game.from(gameId, set);

        game.start();

        assertThat(game.playerOneScore().value()).isEqualTo("0");
        assertThat(game.playerTwoScore().value()).isEqualTo("0");
    }

}
