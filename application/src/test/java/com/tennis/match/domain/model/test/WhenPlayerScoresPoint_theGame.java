package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;

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

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldAskGameScoringRulesToCalculateNewScoreOfPlayerAndOpponent(PlayerNumber playerNumber) {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1), mockMatch);
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        assertThat(game.scoreOf(playerNumber)).isEqualTo(LOVE);

        game.scorePointFor(playerNumber);

        verify(mockGameScoringRules, times(1)).calculateNewScoreOf(playerNumber, game);
        verify(mockGameScoringRules, times(1)).calculateNewScoreOfOpponent(playerNumber, game);
    }

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldUpdateScoreOfPlayerAndOpponent_given_scoringRulesCalculateNewScore(PlayerNumber playerNumber) {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1), mockMatch);
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        assertThat(game.scoreOf(playerNumber)).isEqualTo(LOVE);
        when(mockGameScoringRules.calculateNewScoreOf(playerNumber, game)).thenReturn(THIRTY);
        when(mockGameScoringRules.calculateNewScoreOfOpponent(playerNumber, game)).thenReturn(FIFTEEN);

        game.scorePointFor(playerNumber);

        assertThat(game.scoreOf(playerNumber)).isEqualTo(THIRTY);
        assertThat(game.scoreOf(playerNumber.opponent())).isEqualTo(FIFTEEN);
    }

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldAskGameScoringRulesToCCheckForDeuce(PlayerNumber playerNumber) {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1), mockMatch);
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        assertThat(game.scoreOf(playerNumber)).isEqualTo(LOVE);
        when(mockGameScoringRules.calculateNewScoreOf(playerNumber, game)).thenReturn(THIRTY);
        when(mockGameScoringRules.calculateNewScoreOfOpponent(playerNumber, game)).thenReturn(FIFTEEN);

        game.scorePointFor(playerNumber);

        verify(mockGameScoringRules, times(1)).isDeuce(game);
    }

    @ParameterizedTest
    @EnumSource(PlayerNumber.class)
    void shouldActivateDeuceScoringRules_given_thereIsDeuce(PlayerNumber playerNumber) {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1), mockMatch);
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        assertThat(game.scoreOf(playerNumber)).isEqualTo(LOVE);
        when(mockGameScoringRules.isDeuce(game)).thenReturn(true);
        when(mockGameScoringRules.calculateNewScoreOfOpponent(playerNumber, game)).thenReturn(FORTY);

        game.scorePointFor(playerNumber);

        assertThat(game.gameRules()).isInstanceOf(DeuceGameScoringRules.class);
    }

}
