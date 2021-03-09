package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.Points.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenPlayerScoresPoint_theGame {

    @Mock
    private GameScoringRules mockGameScoringRules;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    private static class TestableGame extends Game {
        public TestableGame(GameId gameId, TennisMatchSet set, GameScoringRules gameScoringRules) {
            super(gameId, set);
            setGameScoringRules(gameScoringRules);
        }
    }

    @Test
    void shouldAskGameScoringRulesToScorePoint() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1));
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(LOVE);

        game.scorePointFor(PLAYER_ONE);

        verify(mockGameScoringRules, times(1)).scorePoint(game, PLAYER_ONE);
    }

    @Test
    void shouldUpdateSetScore_given_playerWinsTheGame() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1));
        Game game = Game.from(gameId, set);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(LOVE);

        game.scorePointFor(PLAYER_ONE);
        game.scorePointFor(PLAYER_ONE);
        game.scorePointFor(PLAYER_ONE);
        game.scorePointFor(PLAYER_ONE);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(GAME);
        assertThat(set.scoreOf(PLAYER_ONE)).isEqualTo(1);
    }

}
