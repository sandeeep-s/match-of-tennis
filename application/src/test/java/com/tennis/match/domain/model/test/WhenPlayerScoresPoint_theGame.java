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
    private Match mockMatch;

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
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1), mockMatch);
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(LOVE);

        game.scorePointFor(PLAYER_ONE);

        verify(mockGameScoringRules, times(1)).scorePoint(game, PLAYER_ONE);
    }

}
