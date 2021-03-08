package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.Points.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class WhenAddingPointForReceiver_theDeuceGameScoringRules {

    private DeuceGameScoringRules deuceGameScoringRulesUnderTest;
    private Game game;

    @BeforeEach
    void setUp() {
        deuceGameScoringRulesUnderTest = new DeuceGameScoringRules();
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.builder().build();
        game = Game.from(gameId, set);
        game.start();
        game.addPointToServerScore();
        game.addPointToServerScore();
        game.addPointToServerScore();
        game.addPointToReceiverScore();
        game.addPointToReceiverScore();
        game.addPointToReceiverScore();
        assertThat(game.gameRules()).isInstanceOf(DeuceGameScoringRules.class);
    }

    @Test
    void shouldChangeScoreToAdvantage_given_gameIsInDeuce() {

        assertThat(game.isDeuce()).isTrue();

        deuceGameScoringRulesUnderTest.addPointToReceiverScore(game);

        assertThat(game.receiverScore().points()).isEqualTo(ADVANTAGE);
    }

    @Test
    void shouldChangeScoreOfReceiverToGame_given_receiverHasAdvantage() {

        game.addPointToReceiverScore();
        assertThat(game.receiverScore().points()).isEqualTo(ADVANTAGE);
        assertThat(game.serverScore().points()).isEqualTo(FORTY);

        deuceGameScoringRulesUnderTest.addPointToReceiverScore(game);

        assertThat(game.receiverScore().points()).isEqualTo(GAME);
    }


}
