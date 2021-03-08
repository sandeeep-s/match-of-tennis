package com.tennis.match.domain.model.test;

import com.tennis.match.domain.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.tennis.match.domain.model.PlayerNumber.PLAYER_ONE;
import static com.tennis.match.domain.model.PlayerNumber.PLAYER_TWO;
import static com.tennis.match.domain.model.Points.*;
import static com.tennis.match.domain.model.Points.FORTY;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class WhenPlayerScoresPoint_theSimpleGameScoringRules {

    private SimpleGameScoringRules simpleGameScoringRulesUnderTest;
    private Game game;

    @BeforeEach
    void setUp() {
        simpleGameScoringRulesUnderTest = new SimpleGameScoringRules();
        GameId gameId = GameId.from(1);
        TennisMatchSet set = TennisMatchSet.from(SetId.from(1));
        game = Game.from(gameId, set);
    }

    @Test
    void shouldChangeScoreToFifteen_given_currentScoreWasZero() {

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(LOVE);

        simpleGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(FIFTEEN);
    }

    @Test
    void shouldChangeScoreToThirty_given_currentScoreWasFifteen() {

        game.scorePoint(PLAYER_ONE);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(FIFTEEN);

        simpleGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(THIRTY);
    }

    @Test
    void shouldChangeScoreToForty_given_currentScoreWasThirty() {

        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(THIRTY);

        simpleGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(FORTY);
    }

    @Test
    void shouldGame_given_currentScoreWasForty_and_deuceRuleWasNotActivated() {

        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(FORTY);

        simpleGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(GAME);
    }

    @Test
    void shouldFailWithError_given_gameIsAlreadyWon() {

        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PLAYER_ONE);
        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(GAME);

        Throwable thrown = catchThrowable(() -> simpleGameScoringRulesUnderTest.scorePoint(game, PLAYER_ONE));

        assertThat(thrown).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void shouldActivateDeuceRule_given_bothPlayersReachScoreForty() {

        assertThat(game.gameRules()).isInstanceOf(SimpleGameScoringRules.class);

        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PlayerNumber.PLAYER_TWO);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PlayerNumber.PLAYER_TWO);
        game.scorePoint(PLAYER_ONE);
        game.scorePoint(PlayerNumber.PLAYER_TWO);

        assertThat(game.scoreOf(PLAYER_ONE)).isEqualTo(FORTY);
        assertThat(game.scoreOf(PLAYER_TWO)).isEqualTo(FORTY);
        assertThat(game.gameRules()).isInstanceOf(DeuceGameScoringRules.class);
    }

}