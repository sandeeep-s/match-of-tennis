package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.GameId;
import com.tennis.match.domain.model.GameScoringRules;
import com.tennis.match.domain.model.TennisMatchSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static com.tennis.match.domain.model.Points.LOVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenAddingPointForReceiver_theGame {

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
    void shouldAskGameScoringRulesToCalculateNewReceiverScore() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        game.start();
        assertThat(game.serverScore().points()).isEqualTo(LOVE);

        game.addPointToReceiverScore();

        verify(mockGameScoringRules, times(1)).addPointToReceiverScore(game);
    }

}
