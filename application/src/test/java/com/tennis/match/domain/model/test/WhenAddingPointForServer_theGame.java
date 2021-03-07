package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.tennis.match.domain.model.Points.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenAddingPointForServer_theGame {

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
    void shouldAskGameScoringRulesToCalculateNewServerScore() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        game.start();
        assertThat(game.serverScore().points()).isEqualTo(LOVE);

        game.addPointToServerScore();

        verify(mockGameScoringRules, times(1)).addPointToServerScore(game);
    }

}
