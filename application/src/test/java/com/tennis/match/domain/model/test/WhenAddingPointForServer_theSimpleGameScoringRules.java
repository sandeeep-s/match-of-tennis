package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.Points.*;
import static com.tennis.match.domain.model.Points.FORTY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class WhenAddingPointForServer_theSimpleGameScoringRules {

    private SimpleGameScoringRules simpleGameScoringRulesUnderTest;
    private Game game;

    @BeforeEach
    void setUp() {
        simpleGameScoringRulesUnderTest = new SimpleGameScoringRules();
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        game = Game.from(gameId, set);
        game.start();
    }

    @Test
    void shouldChangeScoreToFifteen_given_currentScoreWasZero() {

        assertThat(game.serverScore().points()).isEqualTo(LOVE);

        simpleGameScoringRulesUnderTest.addPointToServerScore(game);

        assertThat(game.serverScore().points()).isEqualTo(FIFTEEN);
    }

    @Test
    void shouldChangeScoreToThirty_given_currentScoreWasFifteen() {

        game.addPointForServer();
        assertThat(game.serverScore().points()).isEqualTo(FIFTEEN);

        simpleGameScoringRulesUnderTest.addPointToServerScore(game);

        assertThat(game.serverScore().points()).isEqualTo(THIRTY);
    }

    @Test
    void shouldChangeScoreToForty_given_currentScoreWasThirty() {

        game.addPointForServer();
        game.addPointForServer();
        assertThat(game.serverScore().points()).isEqualTo(THIRTY);

        simpleGameScoringRulesUnderTest.addPointToServerScore(game);

        assertThat(game.serverScore().points()).isEqualTo(FORTY);
    }

    @Test
    void shouldGame_given_currentScoreWasForty_and_deuceRuleWasNotActivated() {

        game.addPointForServer();
        game.addPointForServer();
        game.addPointForServer();
        assertThat(game.serverScore().points()).isEqualTo(FORTY);

        simpleGameScoringRulesUnderTest.addPointToServerScore(game);

        assertThat(game.serverScore().points()).isEqualTo(GAME);
    }

    @Test
    void shouldFailWithError_given_gameIsAlreadyWon() {

        game.addPointForServer();
        game.addPointForServer();
        game.addPointForServer();
        game.addPointForServer();
        assertThat(game.serverScore().points()).isEqualTo(GAME);

        Throwable thrown = catchThrowable(() -> simpleGameScoringRulesUnderTest.addPointToServerScore(game));

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldActivateDeuceRule_given_bothPlayersReachScoreForty() {

        assertThat(game.gameRules()).isInstanceOf(SimpleGameScoringRules.class);

        game.addPointForServer();
        game.addPointForReceiver();
        game.addPointForServer();
        game.addPointForReceiver();
        game.addPointForServer();
        game.addPointForReceiver();

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
        game.addPointForReceiver();
        game.addPointForServer();
        game.addPointForReceiver();
        game.addPointForServer();
        game.addPointForReceiver();
        assertThat(game.serverScore().points()).isEqualTo(FORTY);

        game.addPointForServer();

        assertThat(game.serverScore().points()).isEqualTo("AD");
    }

}
