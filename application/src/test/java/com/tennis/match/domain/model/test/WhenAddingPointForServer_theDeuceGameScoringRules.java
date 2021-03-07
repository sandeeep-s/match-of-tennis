package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.DeuceGameScoringRules;
import com.tennis.match.domain.model.Game;
import com.tennis.match.domain.model.GameId;
import com.tennis.match.domain.model.TennisMatchSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.Points.ADVANTAGE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class WhenAddingPointForServer_theDeuceGameScoringRules {

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

        deuceGameScoringRulesUnderTest.addPointToServerScore(game);

        assertThat(game.serverScore().points()).isEqualTo(ADVANTAGE);
    }

}
