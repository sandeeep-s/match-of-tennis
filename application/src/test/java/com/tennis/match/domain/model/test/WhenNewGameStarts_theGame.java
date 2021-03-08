package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.LOVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenNewGameStarts_theGame {

    @Test
    void shouldStartWithScoreOfZeroPointForEachPlayer() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1));

        Game game = Game.from(gameId, set);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(LOVE);
        assertThat(game.scoreOf(PLAYER_TWO)).isEqualTo(LOVE);
    }

    @Test
    void shouldActivateSimpleGameRules() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1));

        Game game = Game.from(gameId, set);

        assertThat(game.gameRules()).isInstanceOf(SimpleGameScoringRules.class);
    }


}
