package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;

import static com.tennis.match.domain.model.Points.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class WhenPlayerWinsPoint_theGame {

    @Mock
    private GameScoringRules mockGameScoringRules;
    @Captor
    private ArgumentCaptor<GameScore> currentScoreArgumentCaptor;
    @Captor
    private ArgumentCaptor<GameScore> otherScoreArgumentCaptor;

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
    void shouldAskGameScoringRulesForNewServerScore_give_thePointWasWonByServer() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        game.start();
        assertThat(game.serverScore().points()).isEqualTo(LOVE);

        game.addPointForServer();

        verify(mockGameScoringRules, times(1)).calculateServerScore(game);
    }

    @Test
    void shouldUpdatePlayersScoreToCalculatedScore() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = new TestableGame(gameId, set, mockGameScoringRules);
        game.start();
        assertThat(game.serverScore().points()).isEqualTo(LOVE);
        GameScore mockGameScore = GameScore.of(THIRTY);
        when(mockGameScoringRules.calculateServerScore(game)).thenReturn(mockGameScore);

        game.addPointForServer();

        assertThat(game.serverScore()).isEqualTo(mockGameScore);

    }

    @Test
    void shouldChangeScoreToFifteen_given_currentScoreWasZero() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        assertThat(game.serverScore().points()).isEqualTo(LOVE);

        game.addPointForServer();

        assertThat(game.serverScore().points()).isEqualTo(FIFTEEN);
    }

    @Test
    void shouldChangeScoreToThirty_given_currentScoreWasFifteen() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.addPointForServer();
        assertThat(game.serverScore().points()).isEqualTo(FIFTEEN);

        game.addPointForServer();

        assertThat(game.serverScore().points()).isEqualTo(THIRTY);
    }

    @Test
    void shouldChangeScoreToForty_given_currentScoreWasThirty() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.addPointForServer();
        game.addPointForServer();
        assertThat(game.serverScore().points()).isEqualTo(THIRTY);

        game.addPointForServer();

        assertThat(game.serverScore().points()).isEqualTo(FORTY);
    }

    @Test
    void shouldGame_given_currentScoreWasForty_and_deuceRuleWasNotActivated() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.addPointForServer();
        game.addPointForServer();
        game.addPointForServer();
        assertThat(game.serverScore().points()).isEqualTo(FORTY);

        game.addPointForServer();

        assertThat(game.serverScore().points()).isEqualTo(GAME);
    }

    @Test
    void shouldFailWithError_given_gameIsALreadyWon() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.addPointForServer();
        game.addPointForServer();
        game.addPointForServer();
        game.addPointForServer();
        assertThat(game.serverScore().points()).isEqualTo(GAME);

        Throwable thrown = catchThrowable(() -> game.addPointForServer());

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldActivateDeuceRule_given_bothPlayersReachScoreForty() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        assertThat(game.gameRules()).isInstanceOf(SimpleGameScoringRules.class);

        game.addPointForServer();
        game.playerTwoWinsPoint();
        game.addPointForServer();
        game.playerTwoWinsPoint();
        game.addPointForServer();
        game.playerTwoWinsPoint();

        assertThat(game.serverScore().points()).isEqualTo(FORTY);
        assertThat(game.receiverScore().points()).isEqualTo(FORTY);
        assertThat(game.gameRules()).isInstanceOf(DeuceGameScoringRules.class);
    }


    @Test
    void shouldTakeAdvantage_given_currentScoreWasForty_and_deuceRuleWasActivated() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.addPointForServer();
        game.playerTwoWinsPoint();
        game.addPointForServer();
        game.playerTwoWinsPoint();
        game.addPointForServer();
        game.playerTwoWinsPoint();
        assertThat(game.serverScore().points()).isEqualTo(FORTY);

        game.addPointForServer();

        assertThat(game.serverScore().points()).isEqualTo("AD");
    }

}
