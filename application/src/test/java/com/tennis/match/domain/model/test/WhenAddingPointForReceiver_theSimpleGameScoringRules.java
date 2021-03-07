package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.Points.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class WhenAddingPointForReceiver_theSimpleGameScoringRules {

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

        assertThat(game.receiverScore().points()).isEqualTo(LOVE);

        simpleGameScoringRulesUnderTest.addPointToReceiverScore(game);

        assertThat(game.receiverScore().points()).isEqualTo(FIFTEEN);
    }

    @Test
    void shouldChangeScoreToThirty_given_currentScoreWasFifteen() {

        game.addPointForReceiver();
        assertThat(game.receiverScore().points()).isEqualTo(FIFTEEN);

        simpleGameScoringRulesUnderTest.addPointToReceiverScore(game);

        assertThat(game.receiverScore().points()).isEqualTo(THIRTY);
    }

    @Test
    void shouldChangeScoreToForty_given_currentScoreWasThirty() {

        game.addPointForReceiver();
        game.addPointForReceiver();
        assertThat(game.receiverScore().points()).isEqualTo(THIRTY);

        simpleGameScoringRulesUnderTest.addPointToReceiverScore(game);

        assertThat(game.receiverScore().points()).isEqualTo(FORTY);
    }

    @Test
    void shouldGame_given_currentScoreWasForty_and_deuceRuleWasNotActivated() {

        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        assertThat(game.receiverScore().points()).isEqualTo(FORTY);

        simpleGameScoringRulesUnderTest.addPointToReceiverScore(game);

        assertThat(game.receiverScore().points()).isEqualTo(GAME);
    }

    @Test
    void shouldFailWithError_given_gameIsAlreadyWon() {

        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        assertThat(game.receiverScore().points()).isEqualTo(GAME);

        Throwable thrown = catchThrowable(() -> simpleGameScoringRulesUnderTest.addPointToReceiverScore(game));

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldActivateDeuceRule_given_bothPlayersReachScoreForty() {

        assertThat(game.gameRules()).isInstanceOf(SimpleGameScoringRules.class);

        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();

        assertThat(game.receiverScore().points()).isEqualTo(FORTY);
        assertThat(game.receiverScore().points()).isEqualTo(FORTY);
        assertThat(game.gameRules()).isInstanceOf(DeuceGameScoringRules.class);
    }


    @Test
    void shouldTakeAdvantage_given_currentScoreWasForty_and_deuceRuleWasActivated() {

        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        Game game = Game.from(gameId, set);
        game.start();
        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        game.addPointForReceiver();
        assertThat(game.receiverScore().points()).isEqualTo(FORTY);

        game.addPointForReceiver();

        assertThat(game.receiverScore().points()).isEqualTo("AD");
    }

}
