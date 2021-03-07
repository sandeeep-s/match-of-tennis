package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.Points.LOVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenGameStarts_theGame {

    @Test
    void shouldStartWithScoreOfZeroPointForEachPlayer() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);

        game.start();

        assertThat(game.serverScore().points()).isEqualTo(LOVE);
        assertThat(game.receiverScore().points()).isEqualTo(LOVE);
    }

    @Test
    void shouldActivateSimpleGameRules() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);

        game.start();

        assertThat(game.gameRules()).isInstanceOf(SimpleGameScoringRules.class);
    }


}
